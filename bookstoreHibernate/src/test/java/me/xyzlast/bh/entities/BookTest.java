package me.xyzlast.bh.entities;

/**
 * Created by ykyoon on 2/21/14.
 */
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;
import static org.hamcrest.core.IsNot.*;
import static org.junit.Assert.*;

import me.xyzlast.bh.utils.HibernateSessionFactoryBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

public class BookTest {

    private SessionFactory sessionFactory;

    @Before
    public void setUp() {
        sessionFactory = HibernateSessionFactoryBuilder.build();
    }

    @Test
    public void selectAll() {
        Session session = sessionFactory.openSession();
        List<Book> books = session.createCriteria(Book.class).list();
        for(Book book : books) {
            System.out.println(book.getName());
        }
    }
}
