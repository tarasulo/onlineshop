package internet.shop.dao.jdbc;

import internet.shop.dao.OrderDao;
import internet.shop.lib.Dao;
import internet.shop.model.Item;
import internet.shop.model.Order;
import internet.shop.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

@Dao
public class OrderDaoJdbcImpl extends AbstractDao<Order> implements OrderDao {
    private static String queryOrder = "INSERT INTO orders (user_id) VALUES (?);";
    private static String queryItem = "INSERT INTO orders_items (order_id, item_id) "
            + "VALUES (?, ?);";
    private static String queryGetOrderById = "SELECT * FROM orders WHERE order_id = ?;";
    private static String queryUpdateOrder = "UPDATE orders SET user_id = ? WHERE order_id = ?;";
    private static String queryRemoveOrder = "DELETE FROM orders WHERE order_id = ?;";

    private static final Logger logger = Logger.getLogger(OrderDaoJdbcImpl.class);

    public OrderDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Order create(Order order) {
        try (PreparedStatement statementOrder = connection.prepareStatement(
                queryOrder, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statementOrder.setLong(1, order.getUserId());
            statementOrder.executeUpdate();
            ResultSet generatedKeys = statementOrder.getGeneratedKeys();
            generatedKeys.next();
            Long orderId = generatedKeys.getLong(1);
            order.setId(orderId);
            List<Item> list = order.getItems();
            for (Item item : list) {
                try (PreparedStatement statementItem = connection.prepareStatement(queryItem)) {
                    statementItem.setLong(1, orderId);
                    statementItem.setLong(2, item.getId());
                    statementItem.executeUpdate();
                }
            }
        } catch (SQLException e) {
            logger.error("Can't create order", e);
        }
        return order;
    }

    @Override
    public Order get(Long id) {
        Order order = null;
        try (PreparedStatement statement = connection.prepareStatement(queryGetOrderById)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<Item> list = new ArrayList<>();
            while (resultSet.next()) {
                Long orderId = resultSet.getLong("order_id");
                Long userId = resultSet.getLong("user_id");
                order = new Order(userId);
                order.setId(orderId);
                Long itemId = resultSet.getLong("item_id");
                String name = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                Item item = new Item(name, price);
                item.setId(itemId);
                list.add(item);
            }
            order.setItems(list);
        } catch (SQLException e) {
            logger.error("Error, Can't get order by id ", e);
        }
        return order;
    }

    @Override
    public Order update(Order order) {
        try (PreparedStatement statement = connection.prepareStatement(queryUpdateOrder)) {
            statement.setLong(1, order.getUserId());
            statement.setLong(2, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update order", e);
        }
        return order;
    }

    @Override
    public Order delete(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(queryRemoveOrder)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete order", e);
        }
        return null;
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
