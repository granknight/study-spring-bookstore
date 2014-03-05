package me.xyzlast.mybatis.dao;

/**
 * Created by ykyoon on 3/5/14.
 */
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;
import static org.hamcrest.core.IsNot.*;
import static org.junit.Assert.*;

import me.xyzlast.mybatis.configs.MyBatisConfiguration;
import me.xyzlast.mybatis.constants.BookStatus;
import me.xyzlast.mybatis.entities.Book;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

//@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyBatisConfiguration.class)
@Transactional
public class BookDaoTest {
    @Autowired
    private BookDao bookDao;

    @Test
    public void getAll() {
        List<Book> maps = bookDao.getAll();
        for(Book map : maps) {
            System.out.println(map);
        }
    }

    @Test
    public void getBooks() {
        List<Book> maps = bookDao.listByStatus();
        for(Book map : maps) {
            System.out.println(map);
        }
    }

    @Test
    public void addBook() {
        Book book = new Book();
        book.setName("ykyoon");
        book.setAuthor("Author");
        book.setPublishDate(new Timestamp((new Date()).getTime()));
        book.setStatus(BookStatus.CANRENT);
        bookDao.add(book);
    }

    @Test
    public void count() {
        int count = bookDao.countAll();
        assertThat(count, is(not(0)));
    }

    @Test
    public void update() {
        List<Book> books = bookDao.getAll();
        Book book = books.get(0);

        book.setName("Updated " + book.getName());
        bookDao.update(book);
    }
}
