package me.xyzlast.bh.hibernatedao;

import me.xyzlast.bh.entities.User;
import me.xyzlast.bh.intefaces.UserDao;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by ykyoon on 2/26/14.
 */
@Repository
public class UserDaoImpl extends AbstractSessionFactoryDao<User> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User findByName(final String name) {
        Session session = sessionFactory.getCurrentSession();
        return (User) session.createCriteria(User.class)
                        .add(Restrictions.eq("name", name))
                        .uniqueResult();
    }
}
