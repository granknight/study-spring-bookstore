package me.xyzlast.bookstore;

import me.xyzlast.bookstore.constants.BookStatus;
import me.xyzlast.bookstore.dao.BookDaoImpl;
import me.xyzlast.bookstore.entities.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ykyoon on 12/18/13.
 */
@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class BookDaoImplTest {
    @Autowired
    private ApplicationContext context;
    private BookDaoImpl bookDao;

    private List<Book> getBooks() {
        Book book1 = new Book();
        book1.setId(1);
        book1.setName("book name01");
        book1.setAuthor("autor name 01");
        book1.setComment("comment01");
        book1.setPublishDate(new Date());
        book1.setStatus(BookStatus.CanRent);

        Book book2 = new Book();
        book2.setId(2);
        book2.setName("book name02");
        book2.setAuthor("autor name 02");
        book2.setComment("comment02");
        book2.setPublishDate(new Date());
        book2.setStatus(BookStatus.CanRent);

        Book book3 = new Book();
        book3.setId(3);
        book3.setName("book name03");
        book3.setAuthor("autor name 03");
        book3.setComment("comment03");
        book3.setPublishDate(new Date());
        book3.setStatus(BookStatus.CanRent);

        List<Book> books = Arrays.asList(book1, book2, book3);
        return books;
    }

    private void compareBook(Book book) throws Exception {
        Book dbBook = bookDao.getById(book.getId());
        assertThat(dbBook.getName(), is(book.getName()));
        assertThat(dbBook.getAuthor(), is(book.getAuthor()));
        assertThat(dbBook.getComment(), is(book.getComment()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        assertThat(dateFormat.format(dbBook.getPublishDate()), is(dateFormat.format(book.getPublishDate())));
    }

    @Before
    public void setUp() throws Exception {
        bookDao = (BookDaoImpl) context.getBean("bookDaoImpl");
        bookDao.deleteAll();
        assertThat(bookDao.countAll(), is(0));
    }

    @Test
    public void addAndCount() throws Exception {
        List<Book> books = getBooks();
        int count = 0;
        for(Book book : books) {
            bookDao.add(book);
            count++;
            assertThat(bookDao.countAll(), is(count));
        }
    }

    @Test
    public void update() throws Exception {
        List<Book> books = getBooks();
        int count = 0;
        for(Book book : books) {
            bookDao.add(book);
            count++;
            assertThat(bookDao.countAll(), is(count));
        }

        List<Book> addedBooks = bookDao.getAll();
        for(Book book : addedBooks) {
            book.setName("E_" + book.getName());
            book.setAuthor("E_" + book.getAuthor());
            book.setComment("E_" + book.getComment());

            bookDao.update(book);
            compareBook(book);
        }

    }

    @Test
    public void getAll() throws Exception {
        List<Book> books = getBooks();
        int count = 0;
        for(Book book : books) {
            bookDao.add(book);
            count++;
            assertThat(bookDao.countAll(), is(count));
        }

        List<Book> books2 = bookDao.getAll();
        assertThat(books2.size(), is(books.size()));
    }

    @Test
    public void search() throws Exception {
        List<Book> books = getBooks();
        int count = 0;
        for(Book book : books) {
            bookDao.add(book);
            count++;
            assertThat(bookDao.countAll(), is(count));
        }
    }
}
