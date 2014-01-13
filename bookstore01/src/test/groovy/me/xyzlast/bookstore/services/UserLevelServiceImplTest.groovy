package me.xyzlast.bookstore.services

import me.xyzlast.bookstore.constants.UserLevel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * Created by ykyoon on 1/13/14.
 */
@ContextConfiguration("classpath:applicationContext.xml")
class UserLevelServiceImplTest extends Specification {
    @Autowired
    UserLevelService userLevelService

    def "Point값에 따른 UserLevel 확인"() {
        when:
        def userLevel = userLevelService.getUserLevel(point)
        then:
        userLevel == expectedUserLevel
        where:
        point | expectedUserLevel
        99    | UserLevel.NORMAL
        100   | UserLevel.MVP
        299   | UserLevel.MVP
        300   | UserLevel.MASTER
    }
}
