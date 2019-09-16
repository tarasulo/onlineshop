package internet.shop.service;

import internet.shop.model.Order;
import internet.shop.model.User;

import java.util.List;

public interface UserService {

    User add(User user);

    User get(Long id);

    User update(User user);

    void delete(Long id);

    List<Order> getOrders(Long userId);
}
