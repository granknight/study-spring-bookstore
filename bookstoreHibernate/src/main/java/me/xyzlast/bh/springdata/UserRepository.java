package me.xyzlast.bh.springdata;

import me.xyzlast.bh.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ykyoon on 3/5/14.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
}
