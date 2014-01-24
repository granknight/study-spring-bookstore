package me.xyzlast.bookstore.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by ykyoon on 1/24/14.
 */
public class UppercastHandler implements InvocationHandler {
    private final Object target;

    public UppercastHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String ret = (String) method.invoke(this.target, args);
        return ret.toUpperCase();
    }
}
