package me.xyzlast.web.configs;

import me.xyzlast.web.controllers.MainController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by ykyoon on 3/7/14.
 */
@Configuration
@EnableWebMvc
public class ControllerConfiguration {

    @Bean
    public ViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
        internalResourceViewResolver.setSuffix(".jsp");
        internalResourceViewResolver.setOrder(2);

        return internalResourceViewResolver;
    }

    @Bean(name = "/main")
    public Controller mainController() {
        return new MainController();
    }

}
