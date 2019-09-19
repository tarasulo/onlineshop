package internet.shop.controller;

import internet.shop.lib.Inject;
import internet.shop.service.BucketService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteBucketItemController extends HttpServlet {

    private static final Long DEFAULT_ID = 0L;

    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String itemId = req.getParameter("item_id");
        bucketService.deleteItem(Long.valueOf(bucketService.get(DEFAULT_ID).getId()),
                Long.valueOf(itemId));
        resp.sendRedirect(req.getContextPath() + "/bucket");
    }
}
