package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @noinspection unused
 */
public class IndexPage {
    private final ArticleService articleService = new ArticleService();
    private final UserService userService = new UserService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        putMessage(request, view);
    }

    private void findAllArticles(HttpServletRequest request, Map<String, Object> view) {
        view.put("articles", articleService.findAll());
        view.put("loginById", userService.getLoginByIdMap());
    }

    private void putMessage(HttpServletRequest request, Map<String, Object> view) {
        String message = (String) request.getSession().getAttribute("message");
        boolean error = false;
        if (Strings.isNullOrEmpty(message)) {
            message = (String) request.getSession().getAttribute("errorMessage");
            error = true;
        }
        if (!Strings.isNullOrEmpty(message)) {
            String attribute = error ? "errorMessage" : "message";
            view.put(attribute, message);
            request.getSession().removeAttribute(attribute);
        }
    }
}
