package internet.shop.service.impl;

import internet.shop.dao.OrderDao;
import internet.shop.dao.UserDao;
import internet.shop.lib.Inject;
import internet.shop.lib.Service;
import internet.shop.model.Item;
import internet.shop.model.Order;
import internet.shop.model.User;
import internet.shop.service.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private static OrderDao orderDao;

    @Inject
    private static UserDao userDao;

    @Override
    public Order add(Order order) {
        return orderDao.create(order);
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id);
    }

    @Override
    public Order update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public void remove(Long id) {
        orderDao.delete(id);
    }

    @Override
    public Order completeOrder(List<Item> items, Long userId) {
        Order order = new Order();
        User user = userDao.get(userId);
        order.setUser(user);
        order.setItems(items);
        orderDao.create(order);
        return order;
    }

    @Override
    public Order complete(List<Item> items, User user) {
        return orderDao.complite(items, user);
    }

    @Override
    public List<Order> getAllOrdersForUser(Long userId) {
        return orderDao.getOrdersForUser(userId);
    }
}
