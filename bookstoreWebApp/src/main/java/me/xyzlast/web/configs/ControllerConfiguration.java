package me.xyzlast.web.configs;

import com.ctlok.springframework.web.servlet.view.rythm.RythmConfigurator;
import com.ctlok.springframework.web.servlet.view.rythm.RythmViewResolver;
import me.xyzlast.web.controllers.MainController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by ykyoon on 3/7/14.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
        "me.xyzlast.web.controllers"
})
public class ControllerConfiguration {

//    @Bean
//    public ViewResolver internalResourceViewResolver() {
//        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
//        internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
//        internalResourceViewResolver.setSuffix(".jsp");
//        internalResourceViewResolver.setOrder(2);
//
//        return internalResourceViewResolver;
//    }
//
//    @Bean
//    public TilesConfigurer tilesConfigurer() {
//        TilesConfigurer tilesConfigurer = new TilesConfigurer();
//        tilesConfigurer.setDefinitions("/WEB-INF/tiles-config.xml");
//        tilesConfigurer.setCheckRefresh(true);
//        return tilesConfigurer;
//    }
//
//    @Bean
//    public TilesViewResolver tilesViewResolver() {
//        TilesViewResolver viewResolver = new TilesViewResolver();
//        viewResolver.setOrder(1);
//        return viewResolver;
//    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() throws IOException {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        Properties properties = new Properties();
        properties.put("file.encoding", "UTF-8");
        configurer.setProperties(properties);
        return configurer;
    }


    @Bean
    public RythmConfigurator rythmConfigurator() {
        RythmConfigurator rythmConfigurator = new RythmConfigurator();
        rythmConfigurator.setMode("dev");
        rythmConfigurator.setRootDirectory("/WEB-INF/");

        return rythmConfigurator;
    }

    @Bean
    public RythmViewResolver rythmViewResolver() {
        RythmViewResolver rythmViewResolver = new RythmViewResolver(rythmConfigurator());
        rythmViewResolver.setContentType("text/html;charset=UTF-8");
        rythmViewResolver.setPrefix("/WEB-INF/rythm/");
        rythmViewResolver.setSuffix(".html");

//        Properties properties = new Properties();
//        properties.put("file.encoding", "UTF-8");

        return rythmViewResolver;
    }
}
