package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;

import java.util.List;
import java.util.Map;

public interface ArticleRepository {
    Article find(long id);

    List<Article> findAll();

    void save(Article event);

    void changeHidden(Article article, boolean setHidden);
}