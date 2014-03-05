package me.xyzlast.bh.springdata;

import me.xyzlast.bh.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Created by ykyoon on 3/5/14.
 */
public interface BookRepository extends JpaRepository<Book, Integer>, QueryDslPredicateExecutor<Book> {

}
