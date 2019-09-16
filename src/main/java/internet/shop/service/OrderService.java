package internet.shop.service;

import internet.shop.model.Item;
import internet.shop.model.Order;

import java.util.List;

public interface OrderService {

    Order add(Order order);

    Order get(Long id);

    Order update(Order order);

    void remove(Long id);

    Order completeOrder(List<Item> items, Long userId);

    List<Order> getAllOrdersForUser(Long userId);
}
