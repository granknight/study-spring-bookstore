package me.xyzlast.bh.springdata;

import me.xyzlast.bh.entities.History;
import me.xyzlast.bh.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ykyoon on 3/5/14.
 */
public interface HistoryRepository extends JpaRepository<History, Integer> {
    List<History> findByUserOrderByInsertDateDesc(User user);
}
