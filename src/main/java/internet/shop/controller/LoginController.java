package internet.shop.controller;

import internet.shop.exceptions.AuthenticationException;
import internet.shop.lib.Inject;
import internet.shop.model.Bucket;
import internet.shop.model.User;
import internet.shop.service.BucketService;
import internet.shop.service.UserService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("psw");
        try {
            User user = userService.login(login, password);
            HttpSession session = req.getSession();
            session.setAttribute("userId", user.getId());
            Cookie cookie = new Cookie("Mate", user.getToken());
            Bucket bucket = bucketService.add(new Bucket(user.getId()));
            resp.addCookie(cookie);
            resp.sendRedirect(req.getContextPath() + "/servlet/getAllItems");
        } catch (AuthenticationException e) {
            req.setAttribute("errorMsg", "Incorrect login or password!");
            req.getRequestDispatcher("WEB-INF/views/login.jsp").forward(req, resp);
        }
    }
}
