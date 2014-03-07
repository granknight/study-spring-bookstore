package me.xyzlast.web.configs;

import me.xyzlast.web.domain.Hello;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ykyoon on 3/7/14.
 */
@Configuration
public class EmptyDomainConfiguration {
    @Bean
    public Hello getHello() {
        return new Hello();
    }
}
