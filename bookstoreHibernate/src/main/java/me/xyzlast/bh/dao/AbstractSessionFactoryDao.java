package me.xyzlast.bh.dao;

import me.xyzlast.bh.utils.HibernateAction;
import me.xyzlast.bh.utils.HibernateExecutor;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import java.util.List;

/**
 * Created by ykyoon on 2/26/14.
 */
public abstract class AbstractSessionFactoryDao<T> implements EntityDao<T> {
    protected final HibernateExecutor executor;
    private final Class<?> clazz;

    protected AbstractSessionFactoryDao(Class<?> clazz) {
        this.clazz = clazz;
        this.executor = new HibernateExecutor();
    }


    @Override
    public List<T> getAll() {
        return (List<T>) executor.execute(new HibernateAction() {
            @Override
            public Object doProcess(Session session) {
                return session.createCriteria(clazz).list();
            }
        });
    }

    @Override
    public void deleteAll() {
        executor.execute(new HibernateAction() {
            @Override
            public Object doProcess(Session session) {
                List<T> items = getAll();
                for (T item : items) {
                    session.delete(item);
                }
                return null;
            }
        });
    }

    @Override
    public T getById(final int id) {
        return (T) executor.execute(new HibernateAction() {
            @Override
            public Object doProcess(Session session) {
                return (T)session.get(clazz, id);
            }
        });
    }

    @Override
    public void add(final T entity) {
        executor.execute(new HibernateAction() {
            @Override
            public Object doProcess(Session session) {
                session.save(entity);
                return null;
            }
        });
    }

    @Override
    public void update(final T entity) {
        executor.execute(new HibernateAction() {
            @Override
            public Object doProcess(Session session) {
                session.update(entity);
                return null;
            }
        });
    }

    @Override
    public int countAll() {
        Long count = (Long) executor.execute(new HibernateAction() {
            @Override
            public Object doProcess(Session session) {
                Long count = (Long) session.createCriteria(clazz)
                        .setProjection(Projections.rowCount())
                        .uniqueResult();
                return count;
            }
        });

        if(count == null) {
            return 0;
        } else {
            return count.intValue();
        }
    }
}
