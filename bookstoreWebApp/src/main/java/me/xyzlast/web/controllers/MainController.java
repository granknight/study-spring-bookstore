package me.xyzlast.web.controllers;

import me.xyzlast.web.domain.Hello;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ykyoon on 3/6/14.
 */
public class MainController implements Controller {

    private Hello hello;

    public Hello getHello() {
        return hello;
    }

    public void setHello(Hello hello) {
        this.hello = hello;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String name = request.getParameter("name");
        String result = hello.sayHello(name);
        ModelAndView mv = new ModelAndView();
        mv.addObject("result", result);
        mv.setViewName("index");
        return mv;
    }
}
