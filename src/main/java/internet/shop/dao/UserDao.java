package internet.shop.dao;

import internet.shop.exceptions.AuthenticationException;
import internet.shop.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    User add(User user);

    User get(Long id);

    User update(User userDao);

    void delete(Long id);

    List<User> getAll();

    User login(String login, String psw) throws AuthenticationException;

    Optional<User> getByToken(String token);

    byte[] getSaltByLogin(String login);
}
