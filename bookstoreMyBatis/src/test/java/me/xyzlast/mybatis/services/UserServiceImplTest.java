package me.xyzlast.mybatis.services;

/**
 * Created by ykyoon on 3/5/14.
 */
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.*;
import static org.hamcrest.core.IsNot.*;
import static org.junit.Assert.*;

import me.xyzlast.mybatis.configs.MyBatisConfiguration;
import me.xyzlast.mybatis.constants.BookStatus;
import me.xyzlast.mybatis.constants.UserLevel;
import me.xyzlast.mybatis.dao.BookDao;
import me.xyzlast.mybatis.dao.HistoryDao;
import me.xyzlast.mybatis.dao.UserDao;
import me.xyzlast.mybatis.entities.Book;
import me.xyzlast.mybatis.entities.User;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyBatisConfiguration.class})
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
        assertThat(book.getRentUserId(), is(not(nullValue())));
        return book;
    }

    @Test
    public void testReturnBook() throws Exception {
        Book rentedBook = rent();
        User rentUser = userDao.getById(rentedBook.getRentUserId());
        userService.returnBook(rentUser.getId(), rentedBook.getId());
        rentedBook = bookDao.getById(rentedBook.getId());
        assertThat(rentedBook.getStatus(), is(BookStatus.CANRENT));
        assertThat(rentedBook.getRentUserId(), is(nullValue()));
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
