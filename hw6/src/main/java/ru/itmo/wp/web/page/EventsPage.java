package ru.itmo.wp.web.page;

import ru.itmo.wp.model.service.EventService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused"})
public class EventsPage extends Page {
    private final EventService eventService = new EventService();

    @Override
    protected void action(HttpServletRequest request, Map<String, Object> view) {
        view.put("events", eventService.findAll());
    }
}
