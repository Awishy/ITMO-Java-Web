package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.domain.User;

import java.util.List;

public interface TalkRepository {
    Talk find(long id);

    void save(Talk event);

    List<Talk> findAllMessagesWithUser(User user);
}
