package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.EventService;
import ru.itmo.wp.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@SuppressWarnings({"unused"})
public abstract class Page {
    protected final UserService userService = new UserService();
    protected final EventService eventService = new EventService();
    private HttpServletRequest beforeRequest;

    protected void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }

    protected void before(HttpServletRequest request, Map<String, Object> view) {
        beforeRequest = request;
        putUser(request, view);
        putMessage(request, view);
        view.put("userCount", userService.findCount());
    }

    protected void after(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }

    protected void putMessage(HttpServletRequest request, Map<String, Object> view) {
        String message = getMessage();
        if (!Strings.isNullOrEmpty(message)) {
            view.put("message", message);
            request.getSession().removeAttribute("message");
        }
    }

    protected void putUser(HttpServletRequest request, Map<String, Object> view) {
        User user = getUser();
        if (user != null) {
            view.put("user", user);
        }
    }

    private void setAttribute(String key, Object value) {
        if (beforeRequest != null) {
            HttpSession session = beforeRequest.getSession();
            session.setAttribute(key, value);
        }
    }

    private Object getAttribute(String key) {
        if (beforeRequest != null) {
            HttpSession session = beforeRequest.getSession();
            return session.getAttribute(key);
        }
        return null;
    }

    protected void setMessage(String message) {
        setAttribute("message", message);
    }

    protected String getMessage() {
        return (String) getAttribute("message");
    }

    protected void setUser(User user) {
        setAttribute("user", user);
    }

    protected User getUser() {
        return (User) getAttribute("user");
    }
}
