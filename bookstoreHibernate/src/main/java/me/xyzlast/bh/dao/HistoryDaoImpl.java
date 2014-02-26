package me.xyzlast.bh.dao;

import me.xyzlast.bh.entities.History;
import me.xyzlast.bh.utils.HibernateAction;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created by ykyoon on 2/26/14.
 */
public class HistoryDaoImpl extends AbstractSessionFactoryDao<History> implements HistoryDao {

    public HistoryDaoImpl() {
        super(History.class);
    }

    @Override
    public List<History> listByUser(int userId) {
        return (List<History>) executor.execute(new HibernateAction() {
            @Override
            public Object doProcess(Session session) {
                return session.createCriteria(History.class)
                        .addOrder(Order.asc("user.id"))
                        .list();
            }
        });
    }
}
