package me.xyzlast.bookstore

import me.xyzlast.bookstore.constants.BookStatus
import me.xyzlast.bookstore.dao.BookDao
import me.xyzlast.bookstore.entities.Book
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * Created by ykyoon on 12/18/13.
 */
@ContextConfiguration("classpath:applicationContext.xml")
class BookDaoSpockBooTest extends Specification {
    public static final String PREFIX_BOOK_NAME = "BOOK_NAME_"
    public static final String PREFIX_BOOK_AUTHOR = "BOOK_AUTHOR_"
    public static final String PREFIX_COMMENT = "COMMENT_"

    @Autowired
    ApplicationContext context;
    BookDao bookApp;

    def setup() {
        when:
        bookApp = context.getBean("bookApp", BookDao)
        bookApp.deleteAll()
        then:
        bookApp.countAll() == 0
    }

    def cleanup() {

    }

    def addBooks() {
        for(i in 0..9) {
            Book book = new Book()
            book.id = i
            book.name = PREFIX_BOOK_NAME + i
            book.author = PREFIX_BOOK_AUTHOR + i
            book.comment = PREFIX_COMMENT + i
            book.publishDate = new Date()
            book.rentUserId = null
            book.status = BookStatus.CanRent

            bookApp.add(book)
        }
    }

    def "책 추가 후, 책의 갯수 확인"() {
        when:
        addBooks()
        then:
        bookApp.countAll() == 10
    }

    def "책 추가 후, 추가된 책의 모든 제목에 Updated를 붙이고, 그 결과가 정확한지 체크"() {
        def UPDATE_PREFIX = "UPDATED: "
        when:
        addBooks()
        def books = bookApp.getAll()
        books.each{
            it.name = UPDATE_PREFIX + it.name
            bookApp.update(it)
        }
        then: "모든 책의 첫 머리는 %UPDATE_PREFIX 값이 붙어야지 된다."
        books.every {
            it.name.startsWith(UPDATE_PREFIX) == true
        }
    }

    def "모든 책의 삭제 후, 책의 갯수는 0이 되어야지 된다."() {
        when:
        addBooks()
        def preCount = bookApp.countAll()
        bookApp.deleteAll()
        def afterCount = bookApp.countAll()

        then:
        preCount == 10
        afterCount == 0
    }

    def "책을 정상적으로 추가된 것을 확인한다."() {
        when:
        addBooks()
        def books = bookApp.getAll()
        then:
        books.size() == 10
        books.every {
            it.name.startsWith(PREFIX_BOOK_NAME) == true
            it.author.startsWith(PREFIX_BOOK_AUTHOR) == true
            it.comment.startsWith(PREFIX_COMMENT) == true
        }
    }
}
