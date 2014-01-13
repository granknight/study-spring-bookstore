package me.xyzlast.bookstore

import spock.lang.Specification

/**
 * Created by ykyoon on 12/18/13.
 */
class BookDaoImplMainTest extends Specification {
    def "문자열 변경 테스트"() {
        when:
        BookAppMain app = new BookAppMain()
        app.setSeq(SEQ_NUMBER)
        def outputString = app.getString(INPUT_STRING)
        then:
        outputString == OUTPUT_STRING
        where:
        SEQ_NUMBER | INPUT_STRING | OUTPUT_STRING
        3 | "ABcdEFG" | "abcDefg"
        2 | "abcdefg" | "abCdefg"
        5 | "abcdefg" | "abcdeFg"
        4 | "abcdefg" | "ABCDEFG"
    }
}
