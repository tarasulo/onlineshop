package internet.shop.service;

import internet.shop.model.Item;
import internet.shop.model.Order;
import internet.shop.model.User;

import java.util.List;

public interface OrderService {

    Order add(Order order);

    Order get(Long id);

    Order update(Order order);

    void remove(Long id);

    Order completeOrder(List<Item> items, Long userId);

    Order complete(List<Item> items, User user);

    List<Order> getAllOrdersForUser(Long userId);
}
