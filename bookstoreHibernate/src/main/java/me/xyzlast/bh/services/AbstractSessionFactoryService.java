package me.xyzlast.bh.services;

import me.xyzlast.bh.utils.HibernateSessionFactoryBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by ykyoon on 2/25/14.
 */
public abstract class AbstractSessionFactoryService {
    protected final SessionFactory sessionFactory;

    protected AbstractSessionFactoryService() {
        sessionFactory = HibernateSessionFactoryBuilder.build();
    }

    protected Session getSession() {
        return sessionFactory.openSession();
    }
}
