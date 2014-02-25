package me.xyzlast.bh.services;

import me.xyzlast.bh.constants.BookStatus;
import me.xyzlast.bh.constants.UserLevel;
import me.xyzlast.bh.entities.Book;
import me.xyzlast.bh.entities.History;
import me.xyzlast.bh.entities.User;
import me.xyzlast.bh.utils.HibernateSessionFactoryBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

/**
 * Created by ykyoon on 2/25/14.
 */
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;
import static org.hamcrest.core.IsNot.*;
import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

//@SuppressWarnings("unused")
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:applicationContext.xml")
public class UserServiceImplTest {

    private UserService userService;
    private BookService bookService;
    private SessionFactory sessionFactory;

    @Before
    public void setUp() {
        bookService = new BookServiceImpl();
        userService = new UserServiceImpl();
        userService.setUserLevelService(new UserLevelServiceImpl());
        sessionFactory = HibernateSessionFactoryBuilder.build();

        deleteAll(History.class);
        deleteAll(Book.class);
        deleteAll(User.class);

        addTestData();
    }

    private void addTestData() {
        Session session = sessionFactory.openSession();

        Date now = new Date();

        for(int i = 0 ; i < 10 ; i++) {
            Book book = new Book();
            book.setName("BOOK_NAME_" + i);
            book.setAuthor("AUTHOR_" + i);
            book.setPublishDate(new Timestamp(now.getTime()));
            book.setStatus(BookStatus.CANRENT);
            session.save(book);
        }

        User point99User = new User();
        point99User.setPoint(99);
        point99User.setName("point99");
        point99User.setPassword("password");
        point99User.setLevel(UserLevel.NORMAL);
        session.save(point99User);

        User point299User = new User();
        point299User.setPoint(299);
        point299User.setName("point299");
        point299User.setPassword("password");
        point299User.setLevel(UserLevel.MASTER);
        session.save(point299User);

        User point301User = new User();
        point301User.setPoint(301);
        point301User.setName("point301");
        point301User.setPassword("password");
        point301User.setLevel(UserLevel.MVP);
        session.save(point301User);

        session.flush();
        session.close();
    }

    public void deleteAll(Class<?> clazz) {
        Session session = sessionFactory.openSession();
        List objects = session.createCriteria(clazz).list();
        for(Object obj : objects) {
            session.delete(obj);
        }
        session.flush();
        session.close();
    }



    @Test
    public void testRent() throws Exception {
        User point99User = userService.findByName("point99");
        assertThat(point99User, is(not(nullValue())));
        Book book = bookService.listup().get(0);

        userService.rent(point99User.getId(), book.getId());
        point99User = userService.findByName("point99");

        Session session = sessionFactory.openSession();
        book = (Book) session.get(Book.class, book.getId());

        assertThat(point99User.getPoint(), is(100));
        assertThat(point99User.getLevel(), is(UserLevel.MASTER));
        assertThat(book.getStatus(), is(BookStatus.RENTNOW));
    }

    @Test
    public void testReturnBook() throws Exception {

    }

    @Test
    public void testListup() throws Exception {

    }

    @Test
    public void testGetHistories() throws Exception {

    }

    @Test
    public void testSetUserLevelService() throws Exception {

    }
}
