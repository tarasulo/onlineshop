package internet.shop.controller;

import internet.shop.lib.Inject;
import internet.shop.service.OrderService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteOrderController extends HttpServlet {
    @Inject
    private static OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String orderId = req.getParameter("orders_id");
        orderService.remove(Long.parseLong(orderId));
        resp.sendRedirect(req.getContextPath() + "/servlet/getAllOrders");
    }
}
