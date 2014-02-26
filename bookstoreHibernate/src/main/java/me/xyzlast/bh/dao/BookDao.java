package me.xyzlast.bh.dao;

import me.xyzlast.bh.entities.Book;

import java.util.List;

/**
 * Created by ykyoon on 1/13/14.
 */
public interface BookDao extends EntityDao<Book> {
    List<Book> listByStatus();
}
