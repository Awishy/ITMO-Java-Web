package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.ArticleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @noinspection unused
 */
public class ArticlePage {
    private final ArticleService articleService = new ArticleService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        articleService.checkLoggedUser(request.getSession());
    }

    private void saveArticle(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        HttpSession session = request.getSession();
        articleService.checkLoggedUser(session);

        User user = (User) session.getAttribute("user");
        String title = request.getParameter("title").trim();
        String text = request.getParameter("text").trim();

        articleService.validateArticle(title, text);

        Article article = new Article();
        article.setUserId(user.getId());
        article.setTitle(title);
        article.setText(text);
        articleService.save(article);
    }
}
