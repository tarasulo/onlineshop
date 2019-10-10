package internet.shop.controller;

import internet.shop.lib.Inject;
import internet.shop.service.BucketService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteBucketItemController extends HttpServlet {

    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long itemId = Long.valueOf(req.getParameter("item_id"));
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        bucketService.deleteItem(Long.valueOf(bucketService.get(userId).getId()),
                Long.valueOf(itemId));
        resp.sendRedirect(req.getContextPath() + "/servlet/bucket");
    }
}
