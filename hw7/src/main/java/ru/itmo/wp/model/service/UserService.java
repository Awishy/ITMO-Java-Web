package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import com.google.common.hash.Hashing;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.UserRepository;
import ru.itmo.wp.model.repository.impl.UserRepositoryImpl;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @noinspection UnstableApiUsage
 */
public class UserService {
    private final UserRepository userRepository = new UserRepositoryImpl();
    private static final String PASSWORD_SALT = "1174f9d7bc21e00e9a5fd0a783a44c9a9f73413c";

    public void validateRegistration(User user, String password) throws ValidationException {
        if (Strings.isNullOrEmpty(user.getLogin())) {
            throw new ValidationException("Login is required");
        }
        if (!user.getLogin().matches("[a-z]+")) {
            throw new ValidationException("Login can contain only lowercase Latin letters");
        }
        if (user.getLogin().length() > 20) {
            throw new ValidationException("Login can't be longer than 20 letters");
        }
        if (userRepository.findByLogin(user.getLogin()) != null) {
            throw new ValidationException("Login is already in use");
        }

        if (Strings.isNullOrEmpty(password)) {
            throw new ValidationException("Password is required");
        }
        if (password.length() < 4) {
            throw new ValidationException("Password can't be shorter than 4 characters");
        }
        if (password.length() > 25) {
            throw new ValidationException("Password can't be longer than 25 characters");
        }
    }

    public void register(User user, String password) {
        userRepository.save(user, getPasswordSha(password));
    }

    private String getPasswordSha(String password) {
        return Hashing.sha256().hashBytes((PASSWORD_SALT + password).getBytes(StandardCharsets.UTF_8)).toString();
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User find(long id) {
        return userRepository.find(id);
    }

    public User validateAndFindByLoginAndPassword(String login, String password) throws ValidationException {
        User user = userRepository.findByLoginAndPasswordSha(login, getPasswordSha(password));
        if (user == null) {
            throw new ValidationException("Invalid login or password");
        }
        return user;
    }

    public void changeAdmin(User user, boolean setAdmin) {
        userRepository.changeAdmin(user, setAdmin);
    }

    public Map<Long, String> getLoginByIdMap() {
        return userRepository.getLoginByIdMap();
    }

    public void validateAdmin(HttpSession session, User targetUser) {
        User user = (User) session.getAttribute("user");
        if (user == null || !user.isAdmin()) {
            session.setAttribute("errorMessage", "You should be an admin to do it.");
            throw new RedirectException("/index");
        }

        if (targetUser == null) {
            session.setAttribute("errorMessage", "No such user.");
            throw new RedirectException("/index");
        }
    }
}
