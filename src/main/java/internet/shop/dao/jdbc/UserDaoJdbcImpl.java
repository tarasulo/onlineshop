package internet.shop.dao.jdbc;

import internet.shop.dao.BucketDao;
import internet.shop.dao.RoleDao;
import internet.shop.dao.UserDao;
import internet.shop.exceptions.AuthenticationException;
import internet.shop.lib.Dao;
import internet.shop.lib.Inject;
import internet.shop.model.Bucket;
import internet.shop.model.Role;
import internet.shop.model.User;

import internet.shop.util.HashUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.log4j.Logger;

@Dao
public class UserDaoJdbcImpl extends AbstractDao<User> implements UserDao {
    private static String queryAddUser = "INSERT INTO store.users "
            + "(name, login, password, token, salt) VALUES (?, ?, ?, ?, ?);";
    private static String queryAddRole = "INSERT INTO store.user_role (user_id, role_id) "
            + "VALUES (?, ?);";
    private static String queryGetUser = "SELECT * FROM store.users WHERE user_id = ?;";
    private static String queryUpdateUser = "UPDATE users "
            + "SET name = ?, surname = ?, login = ?, password = ? "
            + "WHERE (user_id = ?);";
    private static String queryRemoveUser = "DELETE FROM store.users WHERE user_id = ?;";
    private static String queryGetAllUsers = "SELECT * FROM users";
    private static String queryLogin = "SELECT * FROM store.users "
            + "WHERE login = ? and password = ?;";
    private static String queryGetByToken = "SELECT * FROM store.users WHERE token = ?;";
    private static String queryGetBySalt = "SELECT salt FROM store.users WHERE login = ?;";
    private static String queryGetRoles = "SELECT r.role_id, r.role_name FROM store.role r "
            + "INNER JOIN store.user_role ur ON r.role_id = ur.role_id WHERE user_id=?;";

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
        try (PreparedStatement statementUsers = connection.prepareStatement(
                queryAddUser, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statementUsers.setString(1, user.getName());
            statementUsers.setString(2, user.getLogin());
            statementUsers.setString(3, user.getPassword());
            statementUsers.setString(4, user.getToken());
            statementUsers.setBytes(5, user.getSalt());
            statementUsers.executeUpdate();
            ResultSet generatedKeys = statementUsers.getGeneratedKeys();
            while (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            }
            for (Role role : user.getRoles()) {
                addRole(user.getId(), role);
            }
        } catch (SQLException e) {
            logger.error("Can't create user", e);
        }
        return user;
    }

    private Role addRole(Long userId, Role role) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(queryAddRole)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, role.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't add role");
        }
        return role;
    }

    @Override
    public User get(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(queryGetUser)) {
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
        try (PreparedStatement statement = connection.prepareStatement(queryUpdateUser)) {
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
        try (PreparedStatement statement = connection.prepareStatement(queryRemoveUser)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete user", e);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(queryGetAllUsers)) {
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
        String hashPasword = HashUtil.hashPassword(psw, getSaltByLogin(queryLogin));
        try (PreparedStatement statement = connection.prepareStatement(queryLogin)) {
            statement.setString(1, login);
            statement.setString(2, hashPasword);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = resultSetUser(resultSet);
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
        Optional<User> optionalUser = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(queryGetByToken)) {
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

    @Override
    public byte[] getSaltByLogin(String login) {
        byte[] result = new byte[0];
        try (PreparedStatement statement = connection.prepareStatement(queryGetBySalt)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getBytes("salt");
            }
        } catch (SQLException e) {
            logger.error("Getting salt by login error", e);
        }
        return result;
    }

    private User resultSetUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("user_id"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setLogin(resultSet.getString("login"));
        user.setToken(resultSet.getString("token"));
        user.setSalt(resultSet.getBytes("salt"));
        user.setRoles(getRoles(resultSet.getLong("user_id")));
        return user;
    }

    public Set<Role> getRoles(Long userId) {
        Set<Role> hashSet = new HashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(queryGetRoles)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long roleId = resultSet.getLong("role_id");
                String roleName = resultSet.getString("role_name");
                Role role = Role.of(roleName);
                role.setId(roleId);
                hashSet.add(role);
            }
        } catch (SQLException e) {
            logger.error("Can't get roles by userId" + userId, e);
        }
        return hashSet;
    }

}
