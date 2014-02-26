package me.xyzlast.bh.jpadao;

import me.xyzlast.bh.intefaces.EntityDao;
import me.xyzlast.bh.utils.JpaAction;
import me.xyzlast.bh.utils.JpaExecutor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by ykyoon on 2/26/14.
 */
public abstract class AbstractJpaDao<T> implements EntityDao<T> {

    protected AbstractJpaDao(String entityName) {
        this.entityName = entityName;
    }

    @Autowired
    protected JpaExecutor executor;
    protected final String entityName;

    @Override
    public List<T> getAll() {
        return (List<T>) executor.execute(new JpaAction() {
            @Override
            public Object execute(EntityManager em) {
                return em.createQuery("from " + entityName).getResultList();
            }
        });
    }

    @Override
    public void deleteAll() {
        executor.execute(new JpaAction() {
            @Override
            public Object execute(EntityManager em) {
                return em.createQuery("delete from " + entityName).executeUpdate();
            }
        });
    }

    @Override
    public T getById(final int id) {
        return (T) executor.execute(new JpaAction() {
            @Override
            public Object execute(EntityManager em) {
                Query query = em.createQuery("from " + entityName + " where id = :id");
                query.setParameter("id", id);
                return query.getSingleResult();
            }
        });
    }

    @Override
    public void add(final T entity) {
        executor.execute(new JpaAction() {
            @Override
            public Object execute(EntityManager em) {
                return em.merge(entity);
            }
        });
    }

    @Override
    public void update(final T entity) {
        executor.execute(new JpaAction() {
            @Override
            public Object execute(EntityManager em) {
                return em.merge(entity);
            }
        });
    }

    @Override
    public int countAll() {
        return 0;
    }
}
