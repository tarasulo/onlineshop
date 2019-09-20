package internet.shop.controller;

import internet.shop.lib.Inject;
import internet.shop.service.BucketService;
import internet.shop.service.UserService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddToBucketController extends HttpServlet {
    private static final Long DEFAULT_USER_ID = 0L;

    @Inject
    static BucketService bucketService;

    @Inject
    static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String itemId = req.getParameter("item_id");
        bucketService.addItem(bucketService.get(DEFAULT_USER_ID).getId(), Long.valueOf(itemId));
        resp.sendRedirect(req.getContextPath() + "/getAllItems");
    }
}
