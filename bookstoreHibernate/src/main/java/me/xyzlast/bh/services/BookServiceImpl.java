package me.xyzlast.bh.services;

import me.xyzlast.bh.dao.BookDao;
import me.xyzlast.bh.entities.Book;
import me.xyzlast.bh.utils.HibernateSessionFactoryBuilder;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
