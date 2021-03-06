package internet.shop.service.impl;

import internet.shop.dao.BucketDao;
import internet.shop.dao.RoleDao;
import internet.shop.dao.UserDao;
import internet.shop.exceptions.AuthenticationException;
import internet.shop.lib.Inject;
import internet.shop.lib.Service;
import internet.shop.model.Role;
import internet.shop.model.User;
import internet.shop.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    private static UserDao userDao;

    @Inject
    private static RoleDao roleDao;

    @Inject
    private static BucketDao bucketDao;

    @Override
    public User add(User user) {
        user.setToken(getToken());
        user.addRole(roleDao.get(Role.RoleName.USER));
        return userDao.add(user);
    }

    private String getToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public User get(Long id) {
        return userDao.get(id);
    }

    @Override
    public User update(User item) {
        return userDao.update(item);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public List getOrders(Long userId) {
        return userDao.get(userId).getOrders();
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User login(String login, String psw) throws AuthenticationException {
        return userDao.login(login, psw);
    }

    @Override
    public Optional<User> getByToken(String token) {
        return userDao.getByToken(token);
    }

    @Override
    public byte[] getSaltByLogin(String login) {
        return userDao.getSaltByLogin(login);
    }
}
