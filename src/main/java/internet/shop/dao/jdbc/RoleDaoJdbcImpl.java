package internet.shop.dao.jdbc;

import internet.shop.dao.RoleDao;
import internet.shop.lib.Dao;
import internet.shop.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

@Dao
public class RoleDaoJdbcImpl extends AbstractDao<Role> implements RoleDao {
    private static String queryGetRole = "SELECT * FROM store.role WHERE role_name =?";

    private static Logger logger = Logger.getLogger(RoleDaoJdbcImpl.class);

    public RoleDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Role get(Role.RoleName roleName) {
        try (PreparedStatement statement =
                     connection.prepareStatement(queryGetRole)) {
            statement.setString(1, String.valueOf(roleName));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                Long roleId = resultSet.getLong("role_id");
                String roleNameDb = resultSet.getString("role_name");
                Role role = Role.of(roleNameDb);
                role.setId(roleId);
                return role;
            }
        } catch (SQLException e) {
            logger.error("Can’t get role with name= " + roleName, e);
        }
        return null;
    }
}
