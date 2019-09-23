package internet.shop.controller;

import internet.shop.lib.Inject;
import internet.shop.model.User;
import internet.shop.service.UserService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User newUser = new User();
        newUser.setLogin(req.getParameter("login"));
        newUser.setName(req.getParameter("user_name"));
        newUser.setSurname(req.getParameter("user_surname"));
        newUser.setPassword(req.getParameter("psw"));
        User user = userService.add(newUser);
        HttpSession session = req.getSession();
        session.setAttribute("userId", user.getId());
        Cookie cookie = new Cookie("Mate", user.getToken());
        resp.addCookie(cookie);
        resp.sendRedirect(req.getContextPath() + "/getAllItems");
    }
}
