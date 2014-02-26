package me.xyzlast.bh.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 * Created by ykyoon on 2/26/14.
 */
@Component
public class JpaExecutor {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Object execute(JpaAction action) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            Object ret = action.execute(em);
            transaction.commit();
            return ret;
        } catch(Exception ex) {
            transaction.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }
}
