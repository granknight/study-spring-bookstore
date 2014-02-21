package me.xyzlast.bh.services;

import me.xyzlast.bh.entities.Book;
import me.xyzlast.bh.utils.HibernateSessionFactoryBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created by ykyoon on 2/21/14.
 */
public class BookServiceImpl implements BookService {
    private SessionFactory sessionFactory;
    public BookServiceImpl() {
        sessionFactory = HibernateSessionFactoryBuilder.build();
    }


    @Override
    public List<Book> listup() {
        Session session = sessionFactory.openSession();
        return session.createCriteria(Book.class)
                .addOrder(Order.asc("status"))
                .list();
    }
}
