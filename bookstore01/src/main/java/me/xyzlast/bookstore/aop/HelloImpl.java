package me.xyzlast.bookstore.aop;

import org.springframework.stereotype.Component;

/**
 * Created by ykyoon on 1/24/14.
 */
@Component
public class HelloImpl implements Hello {
    @ToLowerOutput
    @Override
    public String sayHello(String name) {
        return "Hello " + name + "!";
    }

    @ToLowerOutput
    @Override
    public String sayHi(String name) {
        return "Hi! " + name + "!";
    }

    @ToLowerOutput
    @Override
    public String sayThankYou(String name) {
        return "Thank you, " + name + "!";
    }
}
