package me.xyzlast.bh.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created by ykyoon on 2/21/14.
 */
public class HibernateSessionFactoryBuilder {

    private static SessionFactory sessionFactory;


    public static SessionFactory build() {
        if(sessionFactory == null) {
            Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
            StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
            sb.applySettings(cfg.getProperties());
            StandardServiceRegistry standardServiceRegistry = sb.build();
            sessionFactory = cfg.buildSessionFactory(standardServiceRegistry);
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return build();
    }
}
