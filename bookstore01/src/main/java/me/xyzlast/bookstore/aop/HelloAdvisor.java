package me.xyzlast.bookstore.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by ykyoon on 1/24/14.
 */
@Aspect
@Component
public class HelloAdvisor {
    @Pointcut(value = "@annotation(me.xyzlast.bookstore.aop.ToLowerOutput)")
    private void convertToLowercast() {

    }

    @Around(value = "convertToLowercast()")
    public Object convertResultStringToLower(ProceedingJoinPoint pjp) throws Throwable {
        return pjp.proceed().toString().toLowerCase();
    }
}
