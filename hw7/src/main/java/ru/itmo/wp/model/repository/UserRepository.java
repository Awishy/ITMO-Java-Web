package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.User;

import java.util.List;
import java.util.Map;

public interface UserRepository {
    User find(long id);

    User findByLogin(String login);

    User findByLoginAndPasswordSha(String login, String passwordSha);

    List<User> findAll();

    Map<Long, String> getLoginByIdMap();

    void save(User user, String passwordSha);

    void changeAdmin(User user, boolean setAdmin);
}
