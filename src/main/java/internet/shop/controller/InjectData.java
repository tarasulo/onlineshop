package internet.shop.controller;

import internet.shop.lib.Inject;
import internet.shop.model.Bucket;
import internet.shop.model.Item;
import internet.shop.model.Role;
import internet.shop.model.User;
import internet.shop.service.BucketService;
import internet.shop.service.ItemService;
import internet.shop.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectData extends HttpServlet {
    @Inject
    private static ItemService itemService;
    @Inject
    private static UserService userService;
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User bob = new User("Bob", "Yong", "bobby", "000");
        bob.addRole(Role.of("USER"));
        userService.add(bob);
        User admin = new User("Administrator", "Main", "admin", "111");
        admin.addRole(Role.of("ADMIN"));
        userService.add(admin);

        Item item1 = new Item("Nokia 5202", 999.99);
        Item item2 = new Item("Motorola V3", 759.99);
        Item item3 = new Item("Sony E5", 1299.99);
        itemService.add(item1);
        itemService.add(item2);
        itemService.add(item3);

        Bucket bucket = new Bucket(admin.getId());
        bucketService.add(bucket);
        req.getRequestDispatcher("/WEB-INF/views/inject.jsp").forward(req, resp);
    }
}
