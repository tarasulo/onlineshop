package internet.shop.controller;

import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("Mate")) {
                cookie.setValue("");
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
        }
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath() + "/index");
    }
}
