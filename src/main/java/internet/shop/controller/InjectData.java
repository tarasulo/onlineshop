package internet.shop.controller;

import internet.shop.lib.Inject;
import internet.shop.model.Item;
import internet.shop.model.User;
import internet.shop.service.ItemService;
import internet.shop.service.UserService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InjectData implements ServletContextListener {
    @Inject
    private static ItemService itemService;
    @Inject
    private static UserService userService;

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        userService.add(new User("Default USER", "temp", "temp", "123"));
        userService.add(new User("Bob", "Yong", "bobby", "sdtg6y546"));
        userService.add(new User("Nick", "Nikolas", "Miki", "111"));

        Item item1 = new Item("Nokia 5202", 999.99);
        Item item2 = new Item("Motorola V3", 759.99);
        Item item3 = new Item("Sony E5", 1299.99);
        itemService.add(item1);
        itemService.add(item2);
        itemService.add(item3);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
