package me.xyzlast.bh.hibernatedao;

/**
 * Created by ykyoon on 14. 4. 1.
 */

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import com.mysema.query.jpa.hibernate.HibernateUtil;
import me.xyzlast.bh.configs.HibernateConfiguration;
import me.xyzlast.bh.entities.Book;
import me.xyzlast.bh.intefaces.BookDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfiguration.class})
//@Transactional
public class BookDaoImplTest {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private BookDao bookDao;

    @Before
    public void setUp() {
        assertThat(bookDao, is(not(nullValue())));
    }

    @Test
    public void findList() {
        final List<Book> books = bookDao.getAll();
        for(Book book : books) {
            System.out.println(book.getId());
        }
    }

    @Test
    public void testFirstLevelCache() {
        Session session = sessionFactory.openSession();
        int id = 1892;

        for(int i = 0 ; i < 100 ; i++) {
            Book book = (Book) session.get(Book.class, id);
            System.out.println(book.getName());
            System.out.println(book.getRentUser());
        }

        session.close();
        System.out.println("end of method");
        Statistics statistics = sessionFactory.getStatistics();
        long queryExecuteCount = statistics.getPrepareStatementCount();
        long entityFetchCount = sessionFactory.getStatistics().getEntityFetchCount();
        long secondLevelCacheHitCount = sessionFactory.getStatistics().getSecondLevelCacheHitCount();
        assertThat(queryExecuteCount, is(1L));
    }

    @Test
    @Transactional
    public void testFirstLeveCacheUsingSpring() {
        int id = 1892;
        for(int i = 0 ; i < 100 ; i++) {
            Book book = bookDao.getById(id + i);
            if(book != null) {
                System.out.println(book.getName());
                System.out.println(book.getRentUser());
            }
        }
        System.out.println("end of method");
        Statistics statistics = sessionFactory.getStatistics();
        long queryExecuteCount = statistics.getPrepareStatementCount();
        long entityFetchCount = sessionFactory.getStatistics().getEntityFetchCount();
        long secondLevelCacheHitCount = sessionFactory.getStatistics().getSecondLevelCacheHitCount();
        assertThat(queryExecuteCount, is(100L));
    }

    @Test
    public void testSecondLevelCache() {
        Session session = sessionFactory.openSession();

        Transaction tr = session.beginTransaction();

        int id = 1892;
        Book book;
        book = (Book) session.load(Book.class, id);
        System.out.println(book.getName());
        book = (Book) session.load(Book.class, id);
        System.out.println(book.getName());
//        session.evict(book);
        book = (Book) session.load(Book.class, id);
        System.out.println(book.getName());

        tr.commit();
        session.close();

        long entityFetchCount = sessionFactory.getStatistics().getEntityFetchCount();
        long secondLevelCacheHitCount = sessionFactory.getStatistics().getSecondLevelCacheHitCount();
//        assertThat(entityFetchCount, is(1L));


        System.out.println(sessionFactory.getStatistics().getEntityFetchCount());           //Prints 1
//        System.out.println(sessionFactory.getStatistics().getQueryCacheHitCount());
        System.out.println(sessionFactory.getStatistics().getSecondLevelCacheHitCount());   //Prints 0
    }
}
