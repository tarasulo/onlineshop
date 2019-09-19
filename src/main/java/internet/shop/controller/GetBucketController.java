package internet.shop.controller;

import internet.shop.lib.Inject;
import internet.shop.model.Item;
import internet.shop.service.BucketService;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetBucketController extends HttpServlet {
    private static final Long DEFAULT_ID = 0L;

    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Item> allItems = bucketService.getAllItems(DEFAULT_ID);
        req.setAttribute("items", allItems);
        req.getRequestDispatcher("/WEB-INF/views/bucket.jsp").forward(req, resp);
    }
}
