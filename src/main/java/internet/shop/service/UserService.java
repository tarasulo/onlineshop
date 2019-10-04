package internet.shop.service;

import internet.shop.exceptions.AuthenticationException;
import internet.shop.model.Order;
import internet.shop.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User add(User user);

    User get(Long id);

    User update(User user);

    void delete(Long id);

    List<Order> getOrders(Long userId);

    List<User> getAll();

    User login(String login, String psw) throws AuthenticationException;

    Optional<User> getByToken(String token);

    String getSaltByLogin(String login);
}
