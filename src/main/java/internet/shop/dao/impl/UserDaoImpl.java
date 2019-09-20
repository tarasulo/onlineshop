package internet.shop.dao.impl;

import internet.shop.dao.Storage;
import internet.shop.dao.UserDao;
import internet.shop.lib.Dao;
import internet.shop.model.User;

import java.util.List;
import java.util.NoSuchElementException;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User add(User user) {
        Storage.users.add(user);
        return user;
    }

    @Override
    public User get(Long id) {
        return Storage.users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find user with id " + id));
    }

    @Override
    public User update(User userDao) {
        for (int i = 0; i < Storage.users.size(); i++) {
            if (Storage.users.get(i).getId().equals(userDao.getId())) {
                Storage.users.set(i, userDao);
                return userDao;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public void delete(Long id) {
        Storage.users
                .removeIf(user -> user.getId().equals(id));
    }

    @Override
    public List<User> getAll() {
        return Storage.users;
    }
}
