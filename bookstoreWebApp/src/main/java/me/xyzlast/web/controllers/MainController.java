package me.xyzlast.web.controllers;

import me.xyzlast.web.domain.Hello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ykyoon on 3/6/14.
 */
@Controller
public class MainController {
    @Autowired
    private Hello hello;

    public Hello getHello() {
        return hello;
    }

    public void setHello(Hello hello) {
        this.hello = hello;
    }

    @RequestMapping(value = "/main/index")
    public String getIndex(@RequestParam(value = "name1", defaultValue = "ABC", required = true) String name, Model model) {
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name is empty");
        }
        String result = hello.sayHello(name);
        model.addAttribute("result", result);
        return "index";
    }

}
