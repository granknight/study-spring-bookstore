package me.xyzlast.bookstore.dao

import me.xyzlast.bookstore.constants.ActionType
import me.xyzlast.bookstore.constants.BookStatus
import me.xyzlast.bookstore.constants.UserLevel
import me.xyzlast.bookstore.entities.Book
import me.xyzlast.bookstore.entities.History
import me.xyzlast.bookstore.entities.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * Created by ykyoon on 12/25/13.
 */
@ContextConfiguration("classpath:applicationContext.xml")
class HistoryDaoTest extends Specification {
    public static final String PREFIX_BOOK_NAME = "BOOK_NAME_"
    public static final String PREFIX_BOOK_AUTHOR = "BOOK_AUTHOR_"
    public static final String PREFIX_COMMENT = "COMMENT_"

    @Autowired
    ApplicationContext context

    BookDao bookDao
    UserDao userDao
    HistoryDao historyDao

    def setup() {
        bookDao = context.getBean("bookDao")
        userDao = context.getBean("userDao")
        historyDao = context.getBean("historyDao")
    }

    def addBooks() {
        for(def i in 0..9) {
            Book book = new Book()
            book.name = PREFIX_BOOK_NAME + i
            book.author = PREFIX_BOOK_AUTHOR + i
            book.comment = PREFIX_COMMENT + i
            book.publishDate = new Date()
            book.rentUserId = null
            book.status = BookStatus.CanRent

            bookDao.add(book)
        }
    }

    def addUsers() {
        for(def i in 0..9) {
            def user = new User();
            user.name = "USER_NAME_" + i
            user.password = "PASS_" + i
            user.point = i
            user.level = UserLevel.NORMAL
            userDao.add(user)
        }
    }

    def addData() {
        addBooks()
        addUsers()

        def books = bookDao.getAll()
        def users = userDao.getAll()

        for(def i in 0..9) {
            def history = new History();
            history.bookId = books[i].id
            history.userId = users[i].id
            history.actionType = ActionType.Rent
            history.insertTime = new Date()
            historyDao.add(history)
        }
    }

    def "데이터 삭제 후, 신규 데이터 추가"() {
        when:
        historyDao.deleteAll()
        addData()
        then:
        historyDao.countAll() == 10
    }

    def "데이터 추가 후, 삭제가 정상적으로 되는지 확인"() {
        when:
        historyDao.deleteAll()
        def count1 = historyDao.countAll()
        addData()
        def count2 = historyDao.countAll()
        historyDao.deleteAll()
        def count3 = historyDao.countAll()
        then:
        count1 == 0
        count2 == 10
        count3 == 0
    }
}
