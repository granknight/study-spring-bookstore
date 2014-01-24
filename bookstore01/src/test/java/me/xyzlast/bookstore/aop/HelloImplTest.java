package me.xyzlast.bookstore.aop;

/**
 * Created by ykyoon on 1/24/14.
 */
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;
import static org.hamcrest.core.IsNot.*;
import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Proxy;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@ContextConfiguration(value = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class HelloImplTest {
    @Autowired
    private Hello hello;

    @Test
    public void sayHi() {
        assertThat(hello, is(not(nullValue())));
        String result = hello.sayHi("ykyoon");
        System.out.println(result);
        assertThat(result, is(result.toLowerCase()));
    }

    @Test
    public void buildHelloImplWithProxy() {
        Hello proxiedHello = (Hello) Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[] { Hello.class },
                new UppercastHandler(new HelloImpl()));
        String output = proxiedHello.sayHello("ykyoon");
        System.out.println(output);
        System.out.println(proxiedHello.getClass().getName());
    }
}
