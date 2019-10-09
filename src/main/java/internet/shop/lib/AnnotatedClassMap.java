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
    private static final Logger logger = Logger.getLogger(AnnotatedClassMap.class);
    private static final Map<Class, Object> classMap = new HashMap<>();

    static {
        classMap.put(BucketDao.class, Factory.getBucketDao());
        classMap.put(ItemDao.class, Factory.getItemDao());
        classMap.put(OrderDao.class, Factory.getOrderDao());
        classMap.put(UserDao.class, Factory.getUserDao());
        classMap.put(RoleDao.class, Factory.getRoleDao());
        classMap.put(BucketService.class, Factory.getBucketService());
        classMap.put(ItemService.class, Factory.getItemService());
        classMap.put(OrderService.class, Factory.getOrderService());
        classMap.put(UserService.class, Factory.getUserService());
        logger.info("Data successfully added");
    }

    public static Object getImplementation(Class interfaceClass) {
        return classMap.get(interfaceClass);
    }
}
