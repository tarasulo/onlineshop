package internet.shop.controller;

import internet.shop.lib.Inject;
import internet.shop.model.Bucket;
import internet.shop.service.BucketService;
import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutController extends HttpServlet {

    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("Mate")) {
                cookie.setValue(null);
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
        }
        Long userId = (Long) req.getSession(false).getAttribute("userId");
        req.getSession().invalidate();
        Bucket bucket = bucketService.get(userId);
        bucketService.delete(bucket.getId());
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
