package me.xyzlast.bh.services;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;
import static org.hamcrest.core.IsNot.*;
import static org.junit.Assert.*;

import me.xyzlast.bh.configs.JpaConfiguration;
import me.xyzlast.bh.entities.Book;
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

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { HibernateConfiguration.class })
@ContextConfiguration(classes = {JpaConfiguration.class})
@Transactional
public class BookServiceImplTest {
    @Autowired
    private BookService bookService;

    @Test
    public void listup() {
        List<Book> books = bookService.listup();
        for(Book book : books) {
            System.out.println(book.getName());
        }
    }
}
