package me.xyzlast.mybatis.dao;

/**
 * Created by ykyoon on 3/5/14.
 */
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;
import static org.hamcrest.core.IsNot.*;
import static org.junit.Assert.*;

import me.xyzlast.mybatis.configs.MyBatisConfiguration;
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
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyBatisConfiguration.class)
@Transactional
public class HistoryDaoTest {
    @Autowired
    private HistoryDao historyDao;


    @Test
    public void getAll() {
        List<History> histories = historyDao.getAll();
        for(History history : histories) {
            System.out.println(history);
        }
    }

}
