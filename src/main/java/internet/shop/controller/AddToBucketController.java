package internet.shop.controller;

import internet.shop.lib.Inject;
import internet.shop.model.Bucket;
import internet.shop.model.Item;
import internet.shop.service.BucketService;
import internet.shop.service.ItemService;
import internet.shop.service.UserService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class AddToBucketController extends HttpServlet {

    private static Logger logger = Logger.getLogger(AddToBucketController.class);
    @Inject
    private static BucketService bucketService;

    @Inject
    private static UserService userService;

    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.info(this.getClass().getName() + " start working");
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        Long bucketId = userService.get(userId).getBucket().getId();
        Bucket bucket = bucketService.get(bucketId);
        Item item = itemService.get(Long.valueOf(req.getParameter("item_id")));
        bucketService.addItem(bucketId, item.getId());
        resp.sendRedirect(req.getContextPath() + "/servlet/getAllItems");
    }
}
