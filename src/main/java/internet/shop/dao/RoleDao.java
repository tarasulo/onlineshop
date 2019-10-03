package internet.shop.dao;

import internet.shop.model.Role;

public interface RoleDao {
    Role get(Role.RoleName roleName);
}
