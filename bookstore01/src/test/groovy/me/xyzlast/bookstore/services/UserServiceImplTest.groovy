package me.xyzlast.bookstore.services

import me.xyzlast.bookstore.dao.BookDao
import me.xyzlast.bookstore.dao.UserDao
import me.xyzlast.bookstore.entities.Book
import org.springframework.beans.factory.annotation.Autowired
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


    def setup() {

    }

    def cleanup() {

    }

    def "책대여 확인"() {
        when:
        Book book = new Book()
        
    }
}
