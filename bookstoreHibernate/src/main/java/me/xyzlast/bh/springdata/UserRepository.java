package me.xyzlast.bh.springdata;

import me.xyzlast.bh.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Created by ykyoon on 3/5/14.
 */
public interface UserRepository extends JpaRepository<User, Integer>, QueryDslPredicateExecutor<User> {
    User findByName(String name);
}
