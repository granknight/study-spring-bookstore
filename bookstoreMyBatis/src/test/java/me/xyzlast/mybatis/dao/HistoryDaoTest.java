package me.xyzlast.mybatis.dao;

/**
 * Created by ykyoon on 3/5/14.
 */
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;
import static org.hamcrest.core.IsNot.*;
import static org.junit.Assert.*;

import me.xyzlast.mybatis.entities.History;
import me.xyzlast.mybatis.entities.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.InputStream;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

//@SuppressWarnings("unused")
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:applicationContext.xml")
public class HistoryDaoTest {
    private SqlSessionFactory sessionFactory;

    @Before
    public void setUp() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        sessionFactory = new SqlSessionFactoryBuilder().build(is);
        is.close();
        assertThat(sessionFactory, is(not(nullValue())));
    }

    @Test
    public void getAll() {
        SqlSession session = sessionFactory.openSession();
        HistoryDao historyDao = session.getMapper(HistoryDao.class);
        List<History> histories = historyDao.getAll();

        for(History history : histories) {
            System.out.println(history);
        }
    }

}
