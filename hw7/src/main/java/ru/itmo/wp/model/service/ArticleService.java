package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @noinspection UnstableApiUsage
 */
public class ArticleService {
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();

    public void validateArticle(String title, String text) throws ValidationException {
        if (Strings.isNullOrEmpty(title)) {
            throw new ValidationException("Title is required");
        }
        if (title.length() > 120) {
            throw new ValidationException("Title can't be longer than 120 letters");
        }

        if (Strings.isNullOrEmpty(text)) {
            throw new ValidationException("Text is required");
        }
        if (text.length() > 1000) {
            throw new ValidationException("Text can't be longer than 1000 letters");
        }
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public void save(Article article) {
        articleRepository.save(article);
    }

    public void checkLoggedUser(HttpSession session) {
        if (session.getAttribute("user") == null) {
            session.setAttribute("errorMessage", "You should be logged in.");
            throw new RedirectException("/index");
        }
    }

    public void validateAuthor(HttpSession session, Article article) {
        if (article == null || session.getAttribute("user") == null ||
                ((User) session.getAttribute("user")).getId() != article.getUserId()) {
            session.setAttribute("errorMessage", "You should be author of this article to change it.");
            throw new RedirectException("/index");
        }
    }

    public void changeHidden(Article article, boolean setHidden) {
        articleRepository.changeHidden(article, setHidden);
    }

    public Article find(Long id) {
        return articleRepository.find(id);
    }
}
