package internet.shop.web.filters;

import static internet.shop.model.Role.RoleName.ADMIN;
import static internet.shop.model.Role.RoleName.USER;

import internet.shop.lib.Inject;
import internet.shop.model.Role;
import internet.shop.model.User;
import internet.shop.service.UserService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationFilter implements Filter {

    public static final String EMPTY_STRING = "";
    private Map<String, Role.RoleName> protectedUrls = new HashMap<>();

    @Inject
    private static UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedUrls.put("/servlet/getAllUsers", ADMIN);
        protectedUrls.put("/servlet/deleteUser", ADMIN);
        protectedUrls.put("/servlet/addToBucket", ADMIN);
        protectedUrls.put("/servlet/getAllItems", ADMIN);
        protectedUrls.put("/servlet/getAllItems", USER);
        protectedUrls.put("/servlet/addToBucket", USER);
        protectedUrls.put("/servlet/DeleteBucketItem", USER);
        protectedUrls.put("/servlet/completeOrder", USER);
        protectedUrls.put("/servlet/deleteOrder", USER);
        protectedUrls.put("/servlet/getAllOrders", USER);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        Cookie[] cookies = req.getCookies();

        if (cookies == null) {
            processUnAuthentication(req, resp);
            return;
        }

        String requestedUrl = req.getRequestURI().replace(req.getContextPath(), EMPTY_STRING);
        Role.RoleName roleName = protectedUrls.get(requestedUrl);
        if (roleName == null) {
            processUnAuthentication(req, resp, chain);
            return;
        }

        String token = null;
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("Mate")) {
                token = cookie.getValue();
                break;
            }
        }

        if (token == null) {
            processUnAuthentication(req, resp);
            return;
        } else {
            Optional<User> user = userService.getByToken(token);
            if (user.isPresent()) {
                if (verifyRole(user.get(), roleName)) {
                    processUnAuthentication(req, resp, chain);
                } else {
                    processDenied(req, resp);
                }
            } else {
                processUnAuthentication(req, resp);
                return;
            }
        }
    }

    private void processDenied(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/accessDenied.jsp").forward(req, resp);
    }

    private boolean verifyRole(User user, Role.RoleName roleName) {
        return user.getRoles().stream().anyMatch(r -> r.getRoleName().equals(roleName));
    }

    private void processUnAuthentication(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.sendRedirect(req.getContextPath() + "/login");
    }

    private void processUnAuthentication(HttpServletRequest req,
                                         HttpServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
