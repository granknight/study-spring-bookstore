package me.xyzlast.bh.configs;

import com.jolbox.bonecp.BoneCPDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by ykyoon on 2/26/14.
 */
@Configuration
@PropertySources({
        @PropertySource("classpath:connection.properties")
})
//@ComponentScan(basePackages = { "me.xyzlast.bh.services", "me.xyzlast.bh.hibernatedao" })
@ComponentScan(basePackages = { "me.xyzlast.bh.services", "me.xyzlast.bh.querydsl" })
@EnableTransactionManagement
public class HibernateConfiguration {

    @Autowired
    private Environment env;

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
    public LocalSessionFactoryBean sessionFactory() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));

        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setHibernateProperties(properties);

        localSessionFactoryBean.setPackagesToScan(new String[] { "me.xyzlast.bh.entities" });
        return localSessionFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setDataSource(dataSource());
        transactionManager.setSessionFactory(sessionFactory().getObject());
        transactionManager.setHibernateManagedSession(false);
        return transactionManager;
    }
}
