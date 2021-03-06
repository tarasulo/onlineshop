package internet.shop.controller;

import internet.shop.lib.Inject;
import internet.shop.model.Bucket;
import internet.shop.model.User;
import internet.shop.service.BucketService;
import internet.shop.service.UserService;
import internet.shop.util.HashUtil;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class RegistrationController extends HttpServlet {
    private static Logger logger = Logger.getLogger(RegistrationController.class);
    @Inject
    private static UserService userService;
    @Inject
    private static BucketService bucketService;

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
        String password = req.getParameter("psw");
        byte[] salt = HashUtil.getSalt();
        String hashedPassword = HashUtil.hashPassword(password, salt);
        newUser.setPassword(hashedPassword);
        newUser.setSalt(salt);
        newUser.setToken(UUID.randomUUID().toString());

        newUser = userService.add(newUser);
        Bucket bucket = new Bucket();
        bucket.setUser(newUser);
        bucket = bucketService.add(bucket);
        Cookie cookie = new Cookie("Mate", newUser.getToken());
        resp.addCookie(cookie);
        HttpSession session = req.getSession(true);
        session.setAttribute("loggedInUser", newUser);
        logger.info("User " + newUser.getLogin() + " registered");
        resp.sendRedirect(req.getContextPath() + "/servlet/getAllItems");
    }
}
