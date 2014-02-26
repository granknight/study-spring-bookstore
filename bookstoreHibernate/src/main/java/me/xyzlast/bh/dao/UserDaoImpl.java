package me.xyzlast.bh.dao;

import me.xyzlast.bh.entities.User;
import me.xyzlast.bh.utils.HibernateAction;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Created by ykyoon on 2/26/14.
 */
public class UserDaoImpl extends AbstractSessionFactoryDao<User> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User findByName(final String name) {
        return (User) executor.execute(new HibernateAction() {
            @Override
            public Object doProcess(Session session) {
                return session.createCriteria(User.class)
                        .add(Restrictions.eq("name", name))
                        .uniqueResult();
            }
        });
    }
}
