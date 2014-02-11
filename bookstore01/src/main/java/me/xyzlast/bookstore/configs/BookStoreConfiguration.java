package me.xyzlast.bookstore.configs;

import com.jolbox.bonecp.BoneCPDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by ykyoon on 2/11/14.
 */
@Configuration
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
@PropertySource(value = "classpath:connection.properties")
@ComponentScan(basePackages = { "me.xyzlast.bookstore.services", "me.xyzlast.bookstore.dao"})
public class BookStoreConfiguration {
    @Autowired
    private Environment env;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
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
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(dataSource());
        return template;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource());
        return transactionManager;
    }
}
