package me.xyzlast.bookstore.dao;

import me.xyzlast.bookstore.entities.BaseEntity;

import java.util.List;

/**
 * Created by ykyoon on 12/25/13.
 */
public interface EntityDao<T> {
    List<T> getAll();
    void deleteAll();
    T getById(int id);
    void add(T entity);
    void update(T entity);
    int countAll();
}
