package me.xyzlast.bh.springdata;

import me.xyzlast.bh.entities.User;
import me.xyzlast.bh.intefaces.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by ykyoon on 3/5/14.
 */
@Repository
public class UserDaoImpl extends AbstractSpringDataDao<User> implements UserDao {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }
}
