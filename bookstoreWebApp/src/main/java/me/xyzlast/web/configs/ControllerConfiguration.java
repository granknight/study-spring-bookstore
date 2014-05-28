package me.xyzlast.web.configs;

//import com.ctlok.springframework.web.servlet.view.rythm.RythmConfigurator;
//import com.ctlok.springframework.web.servlet.view.rythm.RythmViewResolver;
//import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
import org.springframework.web.servlet.view.velocity.VelocityConfig;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

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
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() throws IOException {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/lib/**").addResourceLocations("/lib/**");
        super.addResourceHandlers(registry);
    }

    @Bean
    public ViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
        internalResourceViewResolver.setSuffix(".jsp");
        internalResourceViewResolver.setOrder(999);

        return internalResourceViewResolver;
    }

    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions("/WEB-INF/tiles-config.xml");
        tilesConfigurer.setCheckRefresh(true);
        return tilesConfigurer;
    }

    @Bean
    public TilesViewResolver tilesViewResolver() {
        TilesViewResolver viewResolver = new TilesViewResolver();
        viewResolver.setOrder(1);
        return viewResolver;
    }

    @Bean
    public VelocityConfig velocityConfig() {
        VelocityConfigurer configurer = new VelocityConfigurer();
        configurer.setResourceLoaderPath("/WEB-INF/velocity");
        Properties properties = new Properties();
        properties.put("input.encoding", "UTF-8");
        properties.put("output.encoding", "UTF-8");
        configurer.setVelocityProperties(properties);
        return configurer;
    }

    @Bean
    public VelocityViewResolver velocityViewResolver() {
        VelocityViewResolver viewResolver = new VelocityViewResolver();
        viewResolver.setContentType("text/html;charset=UTF-8");
        viewResolver.setSuffix(".vm");
        viewResolver.setOrder(2);

        return viewResolver;
    }

    @Bean
    public FreeMarkerConfig freeMarkerConfig() {
        FreeMarkerConfigurer freemarkerConfigurer = new FreeMarkerConfigurer();
        freemarkerConfigurer.setTemplateLoaderPath("/WEB-INF/freemarker");
        Properties properties = new Properties();
        properties.put("default_encoding", "UTF-8");
        freemarkerConfigurer.setFreemarkerSettings(properties);

        return freemarkerConfigurer;
    }

    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver() {
        FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
        freeMarkerViewResolver.setContentType("text/html;charset=UTF-8");
        freeMarkerViewResolver.setSuffix(".ftl");
        freeMarkerViewResolver.setOrder(3);

        return freeMarkerViewResolver;
    }

//    @Bean
//    public RythmConfigurator rythmConfigurator() {
//        RythmConfigurator rythmConfigurator = new RythmConfigurator();
//        rythmConfigurator.setMode("dev");
//        rythmConfigurator.setRootDirectory("/WEB-INF/");
//
//        return rythmConfigurator;
//    }
//
//    @Bean
//    public RythmViewResolver rythmViewResolver() {
//        RythmViewResolver rythmViewResolver = new RythmViewResolver(rythmConfigurator());
//        rythmViewResolver.setContentType("text/html;charset=UTF-8");
//        rythmViewResolver.setPrefix("/WEB-INF/rythm/");
//        rythmViewResolver.setSuffix(".html");
//        rythmViewResolver.setOrder(4);
//        return rythmViewResolver;
//    }

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

    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }
}
