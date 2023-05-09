package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.service.ArticleService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @noinspection unused
 */
public class MyArticlesPage {
    private final ArticleService articleService = new ArticleService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        articleService.checkLoggedUser(request.getSession());
        view.put("articles", articleService.findAll());
    }

    private void changeHidden(HttpServletRequest request, Map<String, Object> view, boolean setHidden) {
        Article article = articleService.find(Long.parseLong(request.getParameter("articleId")));
        articleService.validateAuthor(request.getSession(), article);
        articleService.changeHidden(article, setHidden);
    }

    private void setHidden(HttpServletRequest request, Map<String, Object> view) {
        changeHidden(request, view, true);
    }

    private void unsetHidden(HttpServletRequest request, Map<String, Object> view) {
        changeHidden(request, view, false);
    }
}
