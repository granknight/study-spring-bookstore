package me.xyzlast.bh.jpadao;

import me.xyzlast.bh.entities.User;
import me.xyzlast.bh.intefaces.UserDao;
import me.xyzlast.bh.utils.JpaAction;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Created by ykyoon on 2/26/14.
 */
@Repository
public class UserDaoImpl extends AbstractJpaDao<User> implements UserDao {
    protected UserDaoImpl() {
        super("User");
    }

    @Override
    public User findByName(final String name) {
        Query query = em.createQuery("from User where name = :name");
        query.setParameter("name", name);
        return (User) query.getSingleResult();
    }
}
