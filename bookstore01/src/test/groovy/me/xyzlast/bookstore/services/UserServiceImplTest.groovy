package me.xyzlast.bookstore.services

import me.xyzlast.bookstore.constants.BookStatus
import me.xyzlast.bookstore.constants.UserLevel
import me.xyzlast.bookstore.dao.BookDao
import me.xyzlast.bookstore.dao.HistoryDao
import me.xyzlast.bookstore.dao.UserDao
import me.xyzlast.bookstore.entities.Book
import me.xyzlast.bookstore.entities.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * Created by ykyoon on 1/13/14.
 */
@ContextConfiguration("classpath:applicationContext.xml")
class UserServiceImplTest extends Specification {
    @Autowired
    UserService userService
    @Autowired
    BookDao bookDao
    @Autowired
    UserDao userDao
    @Autowired
    HistoryDao historyDao

    def setup() {
        when:
        historyDao.deleteAll()
        userDao.deleteAll()
        bookDao.deleteAll()
        addData()
        then:
        userDao.countAll() == 3
        bookDao.countAll() == 10
    }

    def cleanup() {

    }

    def addData() {
        for(def i = 0 ; i < 10 ; i++) {
            Book book = new Book()
            book.name = "BOOK_NAME_" + i
            book.author = "AUTHOR_" + i
            book.publishDate = new Date()
            book.rentUserId = null
            book.status = BookStatus.CanRent

            bookDao.add(book)
        }

        User point99User = new User()
        point99User.point = 99
        point99User.name = "point99"
        point99User.password = "password"
        point99User.level = UserLevel.NORMAL
        userDao.add(point99User)

        User point299User = new User()
        point299User.point = 299
        point299User.name = "point299"
        point299User.password = "password"
        point299User.level = UserLevel.MASTER
        userDao.add(point299User)

        User point301User = new User()
        point301User.point = 301
        point301User.name = "point301"
        point301User.password = "password"
        point301User.level = UserLevel.MVP
        userDao.add(point301User)
    }

    def "책대여 확인"() {
        when:
        User point99User = userDao.findByName("point99")
        List<Book> books = bookDao.getAll()
        Book book = books.get(0)
        def result = userService.rent(point99User.id, book.id)
        point99User = userDao.findByName("point99")
        def afterRentPoint = point99User.point
        def afterUserLevel = point99User.level
        book = bookDao.getById(book.getId())

        then:
        point99User != null
        result == true
        afterRentPoint == 100
        afterUserLevel == UserLevel.MASTER
        book.status == BookStatus.RentNow
    }

    def "책대여 후, Point에 대한 정산 및 UserLevel이 정상적으로 변경되고 있는지 확인"() {
        when:
        User user = userDao.findByName(사용자이름)
        def preRentPoint = user.point
        List<Book> books = bookDao.getAll()
        Book book = books.get(0)
        def result = userService.rent(user.id, book.id)
        user = userDao.findByName(사용자이름)
        def afterRentPoint = user.point
        def afterUserLevel = user.level
        book = bookDao.getById(book.getId())

        then:
        user != null
        result == true
        preRentPoint == 예상조회포인트
        afterRentPoint == 예상포인트
        afterUserLevel == 사용자레벨
        book.status == BookStatus.RentNow

        where:
        사용자이름   | 예상조회포인트 | 예상포인트 | 사용자레벨
        "point99"  | 99     | 100   | UserLevel.MASTER
        "point299" | 299    | 300   | UserLevel.MVP
        "point301" | 301    | 302   | UserLevel.MVP
    }

    @DirtiesContext
    def "Transaction 테스트"() {
        when:
        userService.setUserLevelService(new MockUserLevelService())
        User point99User = userDao.findByName("point99")
        List<Book> books = bookDao.getAll()
        Book book = books.get(0)
        userService.rent(point99User.id, book.id)

        then:
        thrown(IllegalArgumentException)
        //NOTE : Transaction이 정상적으로 동작한 경우, BOOK이 update가 되지 않아야지 된다.
        bookDao.getById(book.getId()).status == BookStatus.CanRent
    }

    class MockUserLevelService implements UserLevelService {
        @Override
        UserLevel getUserLevel(int point) {
            throw new IllegalArgumentException("IllegalArgument Exception")
        }
    }
}
