package ru.itmo.wp.model.repository;

import java.util.List;

public interface BasicRepository<T> {
    T find(long id);
}
