package me.xyzlast.bookstore.contexts;

/**
 * Created by ykyoon on 2/11/14.
 */
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;
import static org.hamcrest.core.IsNot.*;
import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

//@SuppressWarnings("unused")
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:applicationContext.xml")
public class HelloContextTest {

    @Test
    public void registerApplicationContext() {
        StaticApplicationContext ac = new StaticApplicationContext();
        ac.registerSingleton("hello1", Hello.class);
        Hello hello = ac.getBean("hello1", Hello.class);
        assertNotNull(hello);
        Hello hello2 = ac.getBean("hello1", Hello.class);
        assertThat(hello, is(hello2));
    }

    @Test
    public void registerApplicationContextWithPrototype() {
        StaticApplicationContext ac = new StaticApplicationContext();
        ac.registerPrototype("hello1", Hello.class);
        Hello hello = ac.getBean("hello1", Hello.class);
        assertNotNull(hello);
        Hello hello2 = ac.getBean("hello1", Hello.class);
        assertThat(hello, is(not(hello2)));
    }

    @Test
    public void registerBeanDef() {
        BeanDefinition helloDef = new RootBeanDefinition(Hello.class);
        helloDef.getPropertyValues().add("name", "ykyoon");
        helloDef.getPropertyValues().add("printer", new ConsolePrinter());
        StaticApplicationContext ac = new StaticApplicationContext();
        ac.registerBeanDefinition("hello1", helloDef);

        Hello hello = ac.getBean("hello1", Hello.class);
        assertNotNull(hello);
        assertThat(hello.sayHello(), is("Hello ykyoon"));
    }
}
