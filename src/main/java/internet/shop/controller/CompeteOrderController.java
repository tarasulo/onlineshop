package internet.shop.controller;

import internet.shop.lib.Inject;
import internet.shop.model.Item;
import internet.shop.service.BucketService;
import internet.shop.service.OrderService;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CompeteOrderController extends HttpServlet {
    private static final Long DEFAULT_ID = 0L;

    @Inject
    private static BucketService bucketService;

    @Inject
    private static OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Item> items = bucketService.getAllItems(bucketService.get(DEFAULT_ID).getId());
        orderService.completeOrder(items, DEFAULT_ID);
        bucketService.clear(bucketService.get(DEFAULT_ID).getId());
        resp.sendRedirect(req.getContextPath() + "/getAllOrders");
    }
}
