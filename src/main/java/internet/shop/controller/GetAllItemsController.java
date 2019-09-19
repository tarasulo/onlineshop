package internet.shop.controller;

import internet.shop.lib.Inject;
import internet.shop.model.Item;
import internet.shop.service.ItemService;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllItemsController extends HttpServlet {
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // String userId = req.getParameter("user_id");
        List<Item> items = itemService.getAll();
        req.setAttribute("items", items);
        // req.setAttribute("user_id", userId);
        req.getRequestDispatcher("/WEB-INF/views/allItems.jsp").forward(req,resp);
    }
}
