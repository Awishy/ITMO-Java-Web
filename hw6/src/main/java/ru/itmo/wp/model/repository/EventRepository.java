package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Event;

import java.util.List;

public interface EventRepository {
    Event find(long id);

    List<Event> findAll();

    void save(Event event);
}
