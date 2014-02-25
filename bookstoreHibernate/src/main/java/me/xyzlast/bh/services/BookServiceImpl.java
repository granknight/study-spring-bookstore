package me.xyzlast.bh.services;

import me.xyzlast.bh.entities.Book;
import me.xyzlast.bh.utils.HibernateSessionFactoryBuilder;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ykyoon on 2/21/14.
 */
public class BookServiceImpl extends AbstractSessionFactoryService implements BookService {
    @Override
    public List<Book> listup() {
        Session session = getSession();
        try {
            return session.createCriteria(Book.class)
                    .addOrder(Order.asc("status"))
                    .list();
        } finally {
            session.close();
        }
    }
}
