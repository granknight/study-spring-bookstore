package me.xyzlast.bh.jpadao;

import me.xyzlast.bh.entities.Book;
import me.xyzlast.bh.intefaces.BookDao;
import me.xyzlast.bh.utils.JpaAction;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by ykyoon on 2/26/14.
 */
@Repository
public class BookDaoImpl extends AbstractJpaDao<Book> implements BookDao {
    public BookDaoImpl() {
        super("Book");
    }

    @Override
    public List<Book> listByStatus() {
        return (List<Book>) executor.execute(new JpaAction() {
            @Override
            public Object execute(EntityManager em) {
                return em.createQuery("from Book order by status").getResultList();
            }
        });
    }
}
