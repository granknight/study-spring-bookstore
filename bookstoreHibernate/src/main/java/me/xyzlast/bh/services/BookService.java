package me.xyzlast.bh.services;


import me.xyzlast.bh.entities.Book;

import java.util.List;

/**
 * Created by ykyoon on 1/13/14.
 */
public interface BookService {
    List<Book> listup();
    void add(String name, String author, String description);
}
