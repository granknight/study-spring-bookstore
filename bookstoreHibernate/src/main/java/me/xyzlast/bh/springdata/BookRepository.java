package me.xyzlast.bh.springdata;

import me.xyzlast.bh.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ykyoon on 3/5/14.
 */
public interface BookRepository extends JpaRepository<Book, Integer> {

}
