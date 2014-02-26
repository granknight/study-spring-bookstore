package me.xyzlast.bh.dao;

import me.xyzlast.bh.entities.Book;
import me.xyzlast.bh.utils.HibernateAction;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created by ykyoon on 2/26/14.
 */
public class BookDaoImpl extends AbstractSessionFactoryDao<Book> implements BookDao {
    public BookDaoImpl() {
        super(Book.class);
    }

    @Override
    public List<Book> listByStatus() {
        return (List<Book>) executor.execute(new HibernateAction() {
            @Override
            public Object doProcess(Session session) {
                return session.createCriteria(Book.class)
                        .addOrder(Order.asc("status"))
                        .list();
            }
        });
    }
}
