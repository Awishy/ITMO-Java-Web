package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.EventType;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@SuppressWarnings({"unused"})
public class LogoutPage extends Page {
    @Override
    protected void action(HttpServletRequest request, Map<String, Object> view) {
        HttpSession session = request.getSession();

        if (session.getAttribute("user") != null) {
            Event event = new Event();
            event.setUserId(((User) session.getAttribute("user")).getId());
            event.setType(EventType.LOGOUT);

            eventService.saveEvent(event);

            session.removeAttribute("user");

            setMessage("Good bye. Hope to see you soon!");
        }
        throw new RedirectException("/index");
    }
}
