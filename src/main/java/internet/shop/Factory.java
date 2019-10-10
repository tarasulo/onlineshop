package internet.shop;

import internet.shop.dao.BucketDao;
import internet.shop.dao.ItemDao;
import internet.shop.dao.OrderDao;
import internet.shop.dao.RoleDao;
import internet.shop.dao.UserDao;
import internet.shop.dao.hibernate.ItemDaoHibernateImpl;
import internet.shop.dao.hibernate.RoleDaoHibernateImpl;
import internet.shop.dao.hibernate.UserDaoHibernateImpl;
import internet.shop.dao.jdbc.BucketDaoJdbcImpl;
import internet.shop.dao.jdbc.OrderDaoJdbcImpl;
import internet.shop.service.BucketService;
import internet.shop.service.ItemService;
import internet.shop.service.OrderService;
import internet.shop.service.UserService;
import internet.shop.service.impl.BucketServiceImpl;
import internet.shop.service.impl.ItemServiceImpl;
import internet.shop.service.impl.OrderServiceImpl;
import internet.shop.service.impl.UserServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.log4j.Logger;

public class Factory {

    private static Logger logger = Logger.getLogger(Factory.class);
    private static Connection connection;

    static {
        try {
            InputStream input = Factory.class.getClassLoader()
                    .getResourceAsStream("config.properties");
            Properties prop = new Properties();
            prop.load(input);
            Class.forName(prop.getProperty("jdbc.driver").toString());
            connection =
                    DriverManager.getConnection(prop.getProperty("db.url")
                            + prop.getProperty("credentials"));
        } catch (ClassNotFoundException | SQLException | IOException e) {
            logger.error("Can't establish connection to our DB", e);
        }
    }

    private static ItemDao itemDao;
    private static BucketDao bucketDao;
    private static OrderDao orderDao;
    private static UserDao userDao;
    private static RoleDao roleDao;

    private static ItemService itemService;
    private static BucketService bucketService;
    private static OrderService orderService;
    private static UserService userService;

    public static RoleDao getRoleDao() {
        if (roleDao == null) {
            roleDao = new RoleDaoHibernateImpl();
        }
        return roleDao;
    }

    public static ItemDao getItemDao() {
        if (itemDao == null) {
            itemDao = new ItemDaoHibernateImpl();
        }
        return itemDao;
    }

    public static BucketDao getBucketDao() {
        if (bucketDao == null) {
            bucketDao = new BucketDaoJdbcImpl(connection);
        }
        return bucketDao;
    }

    public static OrderDao getOrderDao() {
        if (orderDao == null) {
            orderDao = new OrderDaoJdbcImpl(connection);
        }
        return orderDao;
    }

    public static UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoHibernateImpl();
        }
        return userDao;
    }

    public static ItemService getItemService() {
        if (itemService == null) {
            itemService = new ItemServiceImpl();
        }
        return itemService;
    }

    public static BucketService getBucketService() {
        if (bucketService == null) {
            bucketService = new BucketServiceImpl();
        }
        return bucketService;
    }

    public static OrderService getOrderService() {
        if (orderService == null) {
            orderService = new OrderServiceImpl();
        }
        return orderService;
    }

    public static UserService getUserService() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }
}
