package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @noinspection unused
 */
public class UsersPage {
    private final UserService userService = new UserService();

    private void updateUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            session.setAttribute("user", userService.find(user.getId()));
        }
    }

    private void action(HttpServletRequest request, Map<String, Object> view) {
        updateUser(request.getSession());
    }

    private void findAll(HttpServletRequest request, Map<String, Object> view) {
        view.put("users", userService.findAll());
    }

    private void findUser(HttpServletRequest request, Map<String, Object> view) {
        view.put("user",
                userService.find(Long.parseLong(request.getParameter("userId"))));
    }

    private void changeAdmin(HttpServletRequest request, Map<String, Object> view, boolean setAdmin) {
        HttpSession session = request.getSession();
        User user = userService.find(Long.parseLong(request.getParameter("userId")));
        updateUser(session);
        userService.validateAdmin(session, user);
        userService.changeAdmin(user, setAdmin);
        updateUser(session);
    }

    private void setAdmin(HttpServletRequest request, Map<String, Object> view) {
        changeAdmin(request, view, true);
    }

    private void unsetAdmin(HttpServletRequest request, Map<String, Object> view) {
        changeAdmin(request, view, false);
    }
}
