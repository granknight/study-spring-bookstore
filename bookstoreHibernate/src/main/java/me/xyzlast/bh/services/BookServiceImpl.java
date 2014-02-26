package me.xyzlast.bh.services;

import me.xyzlast.bh.intefaces.BookDao;
import me.xyzlast.bh.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ykyoon on 2/21/14.
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    public BookServiceImpl() {

    }

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public List<Book> listup() {
        return bookDao.listByStatus();
    }
}
