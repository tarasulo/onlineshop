package internet.shop.controller;

import internet.shop.lib.Inject;
import internet.shop.model.Bucket;
import internet.shop.service.BucketService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddToBucketController extends HttpServlet {

    @Inject
    static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String itemId = req.getParameter("item_id");
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        Bucket bucket = bucketService.get(userId);
        bucketService.addItem(bucket.getId(), Long.valueOf(itemId));
        resp.sendRedirect(req.getContextPath() + "/servlet/getAllItems");
    }
}
