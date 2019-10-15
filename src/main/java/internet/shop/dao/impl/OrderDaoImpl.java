package internet.shop.dao.impl;

import internet.shop.dao.OrderDao;
import internet.shop.dao.Storage;
import internet.shop.lib.Dao;
import internet.shop.model.Item;
import internet.shop.model.Order;
import internet.shop.model.User;

import java.util.List;
import java.util.NoSuchElementException;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order create(Order order) {
        Storage.orders.add(order);
        return order;
    }

    @Override
    public Order get(Long id) {
        return Storage.orders.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find order with id " + id));
    }

    @Override
    public Order update(Order order) {
        for (int i = 0; i < Storage.orders.size(); i++) {
            if (Storage.orders.get(i).getId().equals(order.getId())) {
                Storage.orders.set(i, order);
            }
        }
        return order;
    }

    @Override
    public Order delete(Long id) {
        Order order = get(id);
        Storage.orders.removeIf((o) -> o.getId().equals(id));
        return order;
    }

    @Override
    public List<Order> getOrdersForUser(Long userId) {
        return null;
    }

    @Override
    public Order complite(List<Item> items, User user) {
        return null;
    }
}
