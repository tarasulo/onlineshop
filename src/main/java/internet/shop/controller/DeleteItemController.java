package internet.shop.controller;

import internet.shop.lib.Inject;
import internet.shop.service.ItemService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteItemController extends HttpServlet {
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long itemId = Long.valueOf(req.getParameter("item_id"));
        itemService.delete(itemId);

        resp.sendRedirect(req.getContextPath() + "/servlet/editItems");
    }
}
