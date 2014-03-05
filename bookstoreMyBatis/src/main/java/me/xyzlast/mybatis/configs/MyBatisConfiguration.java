package me.xyzlast.mybatis.configs;

import com.jolbox.bonecp.BoneCPDataSource;
import me.xyzlast.mybatis.dao.BookDao;
import me.xyzlast.mybatis.dao.HistoryDao;
import me.xyzlast.mybatis.dao.UserDao;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by ykyoon on 3/5/14.
 */
@Configuration
@EnableTransactionManagement
@PropertySources({
        @PropertySource("classpath:connection.properties")
})
@MapperScan(basePackageClasses = {
        BookDao.class,
        UserDao.class,
        HistoryDao.class
})
@ComponentScan(basePackages = "me.xyzlast.mybatis.services")
public class MyBatisConfiguration {
    @Autowired
    private Environment env;
    @Autowired
    private ResourceLoader resourceLoader;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() throws IOException {
        PropertySourcesPlaceholderConfigurer configHolder = new PropertySourcesPlaceholderConfigurer();
        return configHolder;
    }

    @Bean
    public DataSource dataSource() {
        BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setUsername(env.getProperty("connect.username"));
        dataSource.setPassword(env.getProperty("connect.password"));
        dataSource.setDriverClass(env.getProperty("connect.driver"));
        dataSource.setJdbcUrl(env.getProperty("connect.url"));
        dataSource.setMaxConnectionsPerPartition(20);
        dataSource.setMinConnectionsPerPartition(3);
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setConfigLocation(resourceLoader.getResource("classpath:mybatis-config.xml"));
        return sqlSessionFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }
}
