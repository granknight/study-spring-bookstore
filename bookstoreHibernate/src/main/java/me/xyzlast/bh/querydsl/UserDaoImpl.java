package me.xyzlast.bh.querydsl;

import me.xyzlast.bh.intefaces.UserDao;
import me.xyzlast.bh.entities.QUser;
import me.xyzlast.bh.entities.User;
import org.springframework.stereotype.Repository;

/**
 * Created by ykyoon on 2/26/14.
 */
@Repository
public class UserDaoImpl extends AbstractQueryDslDao<User, QUser> implements UserDao {
    public UserDaoImpl() {
        super(User.class, QUser.user);
    }

    @Override
    public User findByName(String name) {
        return getSelectQuery().where(q.name.eq(name)).uniqueResult(q);
    }
}
