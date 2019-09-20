package internet.shop.controller;

import internet.shop.lib.Inject;
import internet.shop.model.Bucket;
import internet.shop.model.Item;
import internet.shop.model.User;
import internet.shop.service.BucketService;
import internet.shop.service.ItemService;
import internet.shop.service.OrderService;
import internet.shop.service.UserService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InjectData implements ServletContextListener {
    @Inject
    private static ItemService itemService;
    @Inject
    private static BucketService bucketService;
    @Inject
    private static OrderService orderService;
    @Inject
    private static UserService userService;

    public static final Long DEFAULT_ID = 0L;

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        userService.add(new User("Default USER"));
        userService.add(new User("Bob"));
        userService.add(new User("Nick"));

        Item item1 = new Item("Nokia 5202", 999.99);
        Item item2 = new Item("Motorola V3", 759.99);
        Item item3 = new Item("Sony E5", 1299.99);
        itemService.add(item1);
        itemService.add(item2);
        itemService.add(item3);

        Bucket defBucket = new Bucket(DEFAULT_ID);
        bucketService.add(defBucket);
        bucketService.addItem(defBucket.getId(), item1.getId());
        bucketService.addItem(defBucket.getId(), item3.getId());
        orderService.completeOrder(defBucket.getItems(), DEFAULT_ID);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
