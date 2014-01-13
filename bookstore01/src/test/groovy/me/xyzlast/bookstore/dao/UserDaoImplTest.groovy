package me.xyzlast.bookstore.dao

import me.xyzlast.bookstore.constants.UserLevel
import me.xyzlast.bookstore.entities.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * Created by ykyoon on 12/25/13.
 */
@ContextConfiguration("classpath:applicationContext.xml")
class UserDaoImplTest extends Specification {
    @Autowired
    private ApplicationContext context;
    private UserDaoImpl userDao;

    def setup() {
        when:
        userDao = context.getBean("userDaoImpl", UserDaoImpl);
        userDao.deleteAll()

        then:
        userDao.countAll() == 0
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

    def "사용자 여럿 추가"() {
        when:
        addUsers()
        then:
        userDao.countAll() == 10
    }

    def "사용자 추가 후, 제거"() {
        when:
        addUsers()
        def preCount = userDao.countAll()
        userDao.deleteAll()
        def afterCount = userDao.countAll()

        then:
        preCount == 10
        afterCount == 0
    }

    def "사용자 여럿 추가 후, 변경 업데이트"() {
        when:
        addUsers()
        def users = userDao.getAll()
        users.each{
            it.name = "EDIT_" + it.name
            it.password = "EDIT_" + it.password
            it.level = UserLevel.MASTER
            it.point = 100
            userDao.update(it)
        }

        then:
        def updatedUsers = userDao.getAll()
        updatedUsers.every {
            it.name.startsWith("EDIT_") == true
            it.password.startsWith("EDIT_") == true
            it.level == UserLevel.MASTER
            it.point == 100
        }
    }
}
