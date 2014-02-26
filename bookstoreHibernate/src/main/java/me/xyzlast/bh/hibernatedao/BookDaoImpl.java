package me.xyzlast.bh.hibernatedao;

import me.xyzlast.bh.entities.Book;
import me.xyzlast.bh.intefaces.BookDao;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ykyoon on 2/26/14.
 */
@Repository
public class BookDaoImpl extends AbstractSessionFactoryDao<Book> implements BookDao {
    public BookDaoImpl() {
        super(Book.class);
    }

    @Override
    public List<Book> listByStatus() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Book.class)
                .addOrder(Order.asc("status"))
                .list();
    }
}
