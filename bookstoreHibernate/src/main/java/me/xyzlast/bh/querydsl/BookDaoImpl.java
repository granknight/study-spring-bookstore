package me.xyzlast.bh.querydsl;

import me.xyzlast.bh.intefaces.BookDao;
import me.xyzlast.bh.entities.Book;
import me.xyzlast.bh.entities.QBook;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ykyoon on 2/26/14.
 */
@Repository
public class BookDaoImpl extends AbstractQueryDslDao<Book, QBook> implements BookDao {
    public BookDaoImpl() {
        super(Book.class, QBook.book);
    }

    @Override
    public List<Book> listByStatus() {
        return getSelectQuery().orderBy(q.status.asc()).list(q);
    }
}
