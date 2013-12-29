package me.xyzlast.bookstore.dao;

import me.xyzlast.bookstore.entities.BaseEntity;

import java.util.List;

/**
 * Created by ykyoon on 12/25/13.
 */
public interface EntityDao<T> {
    List<T> getAll() throws Exception;
    void deleteAll() throws Exception;
    T getById(int id) throws Exception;
    void add(T entity) throws Exception;
    void update(T entity) throws Exception;
    int countAll() throws Exception;
}
