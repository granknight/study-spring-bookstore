package me.xyzlast.bookstore.dao;

import me.xyzlast.bookstore.entities.Book;

import java.util.List;

/**
 * Created by ykyoon on 1/13/14.
 */
public interface BookDao extends EntityDao<Book> {
    List<Book> listByStatus();
}
