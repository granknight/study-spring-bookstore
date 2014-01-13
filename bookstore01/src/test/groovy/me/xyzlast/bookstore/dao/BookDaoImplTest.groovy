package me.xyzlast.bookstore.dao

import me.xyzlast.bookstore.constants.BookStatus
import me.xyzlast.bookstore.entities.Book
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * Created by ykyoon on 12/25/13.
 */
@ContextConfiguration("classpath:applicationContext.xml")
class BookDaoImplTest extends Specification {
    public static final String PREFIX_BOOK_NAME = "BOOK_NAME_"
    public static final String PREFIX_BOOK_AUTHOR = "BOOK_AUTHOR_"
    public static final String PREFIX_COMMENT = "COMMENT_"

    @Autowired
    ApplicationContext context;
    BookDaoImpl bookApp;

    def setup() {
        when:
        bookApp = context.getBean(BookDaoImpl)
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

            if((i % 3) == 0) {
                book.status = BookStatus.CanRent
            } else if((i % 3) == 1) {
                book.status = BookStatus.Missing
            } else {
                book.status = BookStatus.RentNow
            }
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

    def "책 리스트를 얻어낼 때, 상태값으로 얻어지는 것을 확인한다."() {
        when:
        addBooks()
        def count = bookApp.countAll()
        def bookList = bookApp.listByStatus()

        then:
        count == 10
        bookList[0].status == BookStatus.CanRent
        bookList[1].status == BookStatus.CanRent
        bookList[2].status == BookStatus.CanRent
        bookList[3].status == BookStatus.CanRent
        bookList[4].status == BookStatus.RentNow
        bookList[5].status == BookStatus.RentNow
        bookList[6].status == BookStatus.RentNow
        bookList[7].status == BookStatus.Missing
        bookList[8].status == BookStatus.Missing
        bookList[9].status == BookStatus.Missing
    }
}