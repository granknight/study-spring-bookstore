package me.xyzlast.web.configs;

import com.ctlok.springframework.web.servlet.view.rythm.RythmConfigurator;
import com.ctlok.springframework.web.servlet.view.rythm.RythmViewResolver;
import me.xyzlast.web.controllers.MainController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.mvc.support.ControllerClassNameHandlerMapping;
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
public class ControllerConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/lib/**").addResourceLocations("/lib/**");
        super.addResourceHandlers(registry);
    }
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
        return new PropertySourcesPlaceholderConfigurer();
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
        return rythmViewResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
        super.addInterceptors(registry);
    }

    @Bean
    public AcceptHeaderLocaleResolver acceptHeaderLocaleResolver() {
        return new AcceptHeaderLocaleResolver();
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasename("classpath:messages");
        messageSource.setFallbackToSystemLocale(false);
        return messageSource;
    }
}
