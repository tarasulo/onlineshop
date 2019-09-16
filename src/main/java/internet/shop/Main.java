package internet.shop;

import internet.shop.lib.Inject;
import internet.shop.lib.Injector;
import internet.shop.model.Bucket;
import internet.shop.model.Item;
import internet.shop.model.Order;
import internet.shop.model.User;
import internet.shop.service.BucketService;
import internet.shop.service.ItemService;
import internet.shop.service.OrderService;
import internet.shop.service.UserService;

import java.util.List;

public class Main {
    static {
        try {
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Inject
    private static BucketService bucketService;

    @Inject
    private static ItemService itemService;

    @Inject
    private static UserService userService;

    @Inject
    private static OrderService orderService;

    public static void main(String[] args) {
        Item xiaomi = new Item("Xiaomi 9T", 9299.99);
        itemService.add(xiaomi);

        User user1 = new User("Nick");
        userService.add(user1);

        Bucket bucket = new Bucket(user1.getId());
        bucketService.add(bucket);
        bucketService.addItem(bucket.getId(), xiaomi.getId());
        orderService.completeOrder(bucket.getItems(), user1.getId());
        bucketService.clear(bucket.getId());

        List<Order> allOrdersForUser = orderService.getAllOrdersForUser(user1.getId());
        allOrdersForUser.forEach(System.out::println);
    }
}
