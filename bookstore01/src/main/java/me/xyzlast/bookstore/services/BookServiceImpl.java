package me.xyzlast.bookstore.services;

import me.xyzlast.bookstore.dao.BookDao;
import me.xyzlast.bookstore.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ykyoon on 1/13/14.
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    @Override
    public List<Book> listup() {
        return bookDao.listByStatus();
    }
}
