package me.xyzlast.bh.hibernatedao;

import me.xyzlast.bh.entities.BaseEntity;
import me.xyzlast.bh.intefaces.EntityDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by ykyoon on 2/26/14.
 */
public abstract class AbstractSessionFactoryDao<T extends BaseEntity> implements EntityDao<T> {

    @Autowired
    protected SessionFactory sessionFactory;
    private final Class<?> clazz;

    protected AbstractSessionFactoryDao(Class<?> clazz) {
        this.clazz = clazz;
    }


    @Override
    public List<T> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(clazz).list();
    }

    @Override
    public void deleteAll() {
        List<T> items = getAll();
        Session session = sessionFactory.getCurrentSession();
        for (T item : items) {
            session.delete(item);
        }
    }

    @Override
    public T getById(final int id) {
        Session session = sessionFactory.getCurrentSession();
        return (T)session.get(clazz, id);
    }

    @Override
    public void add(final T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.save(entity);
    }

    @Override
    public void update(final T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(entity);
    }

    @Override
    public int countAll() {
        Session session = sessionFactory.getCurrentSession();
        Long count = (Long) session.createCriteria(clazz)
                .setProjection(Projections.rowCount())
                .uniqueResult();

        if(count == null) {
            return 0;
        } else {
            return count.intValue();
        }
    }
}
