package me.xyzlast.bh.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Created by ykyoon on 2/26/14.
 */
public class HibernateExecutor {
    private final SessionFactory sessionFactory;

    public HibernateExecutor() {
        sessionFactory = HibernateSessionFactoryBuilder.build();
    }

    public Object execute(HibernateAction action) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Object ret = action.doProcess(session);
            transaction.commit();
            return ret;
        } catch(Exception ex) {
            transaction.rollback();
            throw ex;
        } finally {
            session.close();
        }
    }
}
