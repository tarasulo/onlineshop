package internet.shop.lib;

import internet.shop.Factory;
import internet.shop.dao.BucketDao;
import internet.shop.dao.ItemDao;
import internet.shop.dao.OrderDao;
import internet.shop.dao.RoleDao;
import internet.shop.dao.UserDao;
import internet.shop.service.BucketService;
import internet.shop.service.ItemService;
import internet.shop.service.OrderService;
import internet.shop.service.UserService;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

public class AnnotatedClassMap {
    private static final Logger LOGGER = Logger.getLogger(AnnotatedClassMap.class);
    private static final Map<Class, Object> CLASS_MAP = new HashMap<>();

    static {
        CLASS_MAP.put(BucketDao.class, Factory.getBucketDao());
        CLASS_MAP.put(ItemDao.class, Factory.getItemDao());
        CLASS_MAP.put(OrderDao.class, Factory.getOrderDao());
        CLASS_MAP.put(UserDao.class, Factory.getUserDao());
        CLASS_MAP.put(RoleDao.class, Factory.getRoleDao());
        CLASS_MAP.put(BucketService.class, Factory.getBucketService());
        CLASS_MAP.put(ItemService.class, Factory.getItemService());
        CLASS_MAP.put(OrderService.class, Factory.getOrderService());
        CLASS_MAP.put(UserService.class, Factory.getUserService());
        LOGGER.info("Data successfully added");
    }

    public static Object getImplementation(Class interfaceClass) {
        return CLASS_MAP.get(interfaceClass);
    }
}
