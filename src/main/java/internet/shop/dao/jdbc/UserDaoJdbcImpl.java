package internet.shop.dao.jdbc;

import internet.shop.dao.BucketDao;
import internet.shop.dao.RoleDao;
import internet.shop.dao.UserDao;
import internet.shop.exceptions.AuthenticationException;
import internet.shop.lib.Dao;
import internet.shop.lib.Inject;
import internet.shop.model.Bucket;
import internet.shop.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;

@Dao
public class UserDaoJdbcImpl extends AbstractDao<User> implements UserDao {

    private static Logger logger = Logger.getLogger(UserDaoJdbcImpl.class);
    private static User user = null;
    @Inject
    private static RoleDao roleDao;
    @Inject
    private static BucketDao bucketDao;

    public UserDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public User add(User user) {
        String query = "INSERT INTO users (name, login, password, token) "
                + "VALUES (?, ?, ?, ?);";
        try (PreparedStatement statementUsers = connection.prepareStatement(
                query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statementUsers.setString(1, user.getName());
            statementUsers.setString(2, user.getLogin());
            statementUsers.setString(3, user.getPassword());
            statementUsers.setString(4, user.getToken());
            statementUsers.executeUpdate();
            ResultSet generatedKeys = statementUsers.getGeneratedKeys();
            generatedKeys.next();
            Long userId = generatedKeys.getLong(1);
            user.setId(userId);
        } catch (SQLException e) {
            logger.error("Can't create user", e);
        }
        return user;
    }

    @Override
    public User get(Long id) {
        String query = "SELECT * FROM users WHERE user_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = resultSetUser(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Get user by id error", e);
        }
        return user;
    }

    @Override
    public User update(User user) {
        String query = "UPDATE users "
                + "SET name = ?, surname = ?, login = ?, password = ? "
                + "WHERE (user_id = ?);";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setLong(5, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update user", e);
            return null;
        }
        return user;
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM users WHERE user_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete user", e);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                resultSetUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error("Get users error", e);
        }
        return users;
    }

    @Override
    public User login(String login, String psw) throws AuthenticationException {
        String query = "SELECT * FROM users WHERE login = ? AND password = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            statement.setString(2, psw);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = resultSetUser(resultSet);
                Bucket bucket = bucketDao.getBucketByUserId(user.getId());
                user.setBucket(bucket);
            } else {
                throw new AuthenticationException("Incorrect login or password");
            }
        } catch (SQLException e) {
            logger.error("Checking login or password error", e);
        }
        return user;
    }

    @Override
    public Optional<User> getByToken(String token) {
        String query = "SELECT * FROM users WHERE token = ?;";
        Optional<User> optionalUser = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = resultSetUser(resultSet);
                Bucket bucket = bucketDao.getBucketByUserId(user.getId());
                user.setBucket(bucket);
                optionalUser = Optional.of(user);
            }
        } catch (SQLException e) {
            logger.error("Getting by token error", e);
        }
        return optionalUser;
    }

    private static User resultSetUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("user_id"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setLogin(resultSet.getString("login"));
        user.setToken(resultSet.getString("token"));
        return user;
    }
}
