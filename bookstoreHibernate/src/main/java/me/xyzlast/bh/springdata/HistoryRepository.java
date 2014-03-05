package me.xyzlast.bh.springdata;

import me.xyzlast.bh.entities.History;
import me.xyzlast.bh.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;

/**
 * Created by ykyoon on 3/5/14.
 */
public interface HistoryRepository extends JpaRepository<History, Integer>, QueryDslPredicateExecutor<History> {
    List<History> findByUserOrderByInsertDateDesc(User user);
}
