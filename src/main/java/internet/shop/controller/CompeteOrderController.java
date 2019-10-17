package internet.shop.controller;

import internet.shop.lib.Inject;
import internet.shop.model.Item;
import internet.shop.model.User;
import internet.shop.service.BucketService;
import internet.shop.service.OrderService;
import internet.shop.service.UserService;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CompeteOrderController extends HttpServlet {

    @Inject
    private static BucketService bucketService;

    @Inject
    private static OrderService orderService;

    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        User user = userService.get(userId);
        List<Item> items = bucketService.getAllItems(user.getBucket().getId());
        orderService.complete(items, user);
        bucketService.clear(bucketService.getBucketByUserId(userId).getId());
        resp.sendRedirect(req.getContextPath() + "/servlet/getAllOrders");
    }
}
