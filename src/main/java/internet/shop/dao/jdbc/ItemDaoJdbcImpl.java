package internet.shop.dao.jdbc;

import internet.shop.dao.ItemDao;
import internet.shop.lib.Dao;
import internet.shop.model.Item;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {
    private static final Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);
    private static final String DB_NAME = "store";

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item item) {
        Statement statement = null;
        String query = String.format("Insert into %s.items (name, price) Values ('%s', %f);",
                DB_NAME, item.getName(), item.getPrice());
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            return item;
        } catch (SQLException e) {
            logger.error("Can't create item", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.warn("Can't close statement ", e);
                }
            }
        }
        return null;
    }

    @Override
    public Item get(Long id) {
        Statement statement = null;
        String query = String.format("Select * from %s.items where item_id = %d;", DB_NAME, id);

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Long itemId = resultSet.getLong("item_id");
                String name = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                Item item = new Item(name, price);
                item.setId(itemId);
                return item;
            }
        } catch (SQLException e) {
            logger.warn("Can't get item by id=" + id);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.warn("Can't close statement ", e);
                }
            }
        }
        return null;
    }

    @Override
    public Item update(Item item) {
        Statement statement = null;
        String query = String
                .format("UPDATE %s.items SET name  = '%s', price= %.2f WHERE item_id = %d;",
                        DB_NAME, item.getId(), item.getName(), item.getPrice(), item.getId());
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            logger.error("Can't update item by id " + item.getId(), e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.error("Can't close statement", e);
                }
            }
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Statement statement = null;
        String query = String.format("DELETE FROM %s.items WHERE item_id = %d;", DB_NAME, id);
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            logger.error("Can't delete item by id" + id, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.error("Can't close statement", e);
                }
            }
        }
    }

    @Override
    public Item delete(Item item) {
        return null;
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        Statement statement = null;
        String query = String.format("SELECT * FROM %s.items;", DB_NAME);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                long itemId = resultSet.getLong("item_id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                Item item = new Item(itemId);
                item.setName(name);
                item.setPrice(price);
                items.add(item);
            }
        } catch (SQLException e) {
            logger.error("Can't get items", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.error("Can't close statement", e);
                }
            }
        }
        return items;
    }
}
