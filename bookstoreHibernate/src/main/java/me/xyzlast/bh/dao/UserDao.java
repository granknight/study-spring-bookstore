package me.xyzlast.bh.dao;

import me.xyzlast.bh.entities.User;

/**
 * Created by ykyoon on 1/13/14.
 */
public interface UserDao extends EntityDao<User> {
    User findByName(String name);
}
