package me.xyzlast.bh.services;

import me.xyzlast.bh.configs.HibernateConfiguration;
import me.xyzlast.bh.constants.BookStatus;
import me.xyzlast.bh.constants.UserLevel;
import me.xyzlast.bh.entities.Book;
import me.xyzlast.bh.entities.User;
import me.xyzlast.bh.intefaces.BookDao;
import me.xyzlast.bh.intefaces.HistoryDao;
import me.xyzlast.bh.intefaces.UserDao;
import org.junit.Test;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;
import static org.hamcrest.core.IsNot.*;
import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by ykyoon on 2/25/14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { HibernateConfiguration.class })
@Transactional
public class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    @Autowired
    private BookDao bookDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private HistoryDao historyDao;

    @Before
    public void setUp() {
        historyDao.deleteAll();
        bookDao.deleteAll();
        userDao.deleteAll();

        addTestData();
    }

    private void addTestData() {
        Date now = new Date();

        for(int i = 0 ; i < 10 ; i++) {
            Book book = new Book();
            book.setName("BOOK_NAME_" + i);
            book.setAuthor("AUTHOR_" + i);
            book.setPublishDate(new Timestamp(now.getTime()));
            book.setStatus(BookStatus.CANRENT);
            bookDao.add(book);
        }

        User point99User = new User();
        point99User.setPoint(99);
        point99User.setName("point99");
        point99User.setPassword("password");
        point99User.setLevel(UserLevel.NORMAL);
        userDao.add(point99User);

        User point299User = new User();
        point299User.setPoint(299);
        point299User.setName("point299");
        point299User.setPassword("password");
        point299User.setLevel(UserLevel.MASTER);
        userDao.add(point299User);

        User point301User = new User();
        point301User.setPoint(301);
        point301User.setName("point301");
        point301User.setPassword("password");
        point301User.setLevel(UserLevel.MVP);
        userDao.add(point301User);
    }

    @Test
    public void testRent() throws Exception {
        rent();
    }

    private Book rent() throws Exception {
        User point99User = userDao.findByName("point99");
        assertThat(point99User, is(not(nullValue())));
        Book book = bookService.listup().get(0);

        userService.rent(point99User.getId(), book.getId());
        point99User = userDao.findByName("point99");


        book = bookDao.getById(book.getId());
        assertThat(point99User.getPoint(), is(100));
        assertThat(point99User.getLevel(), is(UserLevel.MASTER));
        assertThat(book.getStatus(), is(BookStatus.RENTNOW));

        return book;
    }

    @Test
    public void testReturnBook() throws Exception {
        Book rentedBook = rent();
        User rentUser = rentedBook.getRentUser();
        userService.returnBook(rentUser.getId(), rentedBook.getId());
        rentedBook = bookDao.getById(rentedBook.getId());
        assertThat(rentedBook.getStatus(), is(BookStatus.CANRENT));
        assertThat(rentedBook.getRentUser(), is(nullValue()));
    }

    @Test
    public void testReturnBookWithException() throws Exception {

    }

    @Test
    public void testListup() throws Exception {
        List<User> listup = userService.listup();
        for(User user : listup) {
            System.out.println(user);
        }
    }

    @Test
    public void testGetHistories() throws Exception {

    }

    @Test
    public void testSetUserLevelService() throws Exception {

    }
}
