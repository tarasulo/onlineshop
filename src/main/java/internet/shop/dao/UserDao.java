package internet.shop.dao;

import internet.shop.model.User;

import java.util.List;

public interface UserDao {

    User add(User user);

    User get(Long id);

    User update(User userDao);

    void delete(Long id);

    List<User> getAll();
}
