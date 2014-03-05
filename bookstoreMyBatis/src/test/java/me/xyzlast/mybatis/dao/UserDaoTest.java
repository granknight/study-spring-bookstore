package me.xyzlast.mybatis.dao;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;
import static org.hamcrest.core.IsNot.*;
import static org.junit.Assert.*;

import me.xyzlast.mybatis.configs.MyBatisConfiguration;
import me.xyzlast.mybatis.constants.BookStatus;
import me.xyzlast.mybatis.entities.Book;
import me.xyzlast.mybatis.entities.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyBatisConfiguration.class)
@Transactional
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void getAll() {
        List<User> users = userDao.getAll();
        for(User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void getUserByName() {
        User user = userDao.findByName("point99");
        System.out.println(user);
    }
}
