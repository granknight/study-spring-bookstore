package me.xyzlast.mybatis.services;

import me.xyzlast.mybatis.dao.BookDao;
import me.xyzlast.mybatis.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
