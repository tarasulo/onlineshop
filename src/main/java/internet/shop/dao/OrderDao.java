package internet.shop.dao;

import internet.shop.model.Item;
import internet.shop.model.Order;
import internet.shop.model.User;

import java.util.List;

public interface OrderDao {

    Order create(Order order);

    Order get(Long id);

    Order update(Order order);

    Order delete(Long id);

    List<Order> getOrdersForUser(Long userId);

    Order complite(List<Item> items, User user);
}
