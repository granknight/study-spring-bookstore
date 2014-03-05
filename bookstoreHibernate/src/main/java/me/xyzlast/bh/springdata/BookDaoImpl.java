package me.xyzlast.bh.springdata;

import me.xyzlast.bh.entities.Book;
import me.xyzlast.bh.intefaces.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ykyoon on 3/5/14.
 */
@Repository
public class BookDaoImpl extends AbstractSpringDataDao<Book> implements BookDao {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> listByStatus() {
        Sort sort = new Sort(Sort.Direction.ASC, "status");
        return bookRepository.findAll(sort);
    }
}
