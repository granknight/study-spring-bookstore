package me.xyzlast.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * Created by ykyoon on 3/10/14.
 * JSTL Example pages
 */
@Controller
public class JstlExampleController {

    @RequestMapping(value = "jstl/out")
    public String getOutExample(Model model) {
        Map<String, String> person = new HashMap<>();
        person.put("name", "윤영권");
        person.put("age", "36");
        model.addAttribute("person", person);
        return "jstl/out";
    }

    @RequestMapping(value = "jstl/if")
    public String getIfExample(Model model) {
        Map<String, Object> person = new HashMap<>();
        person.put("name", "윤영권");
        person.put("married", true);
        person.put("hasChildren", true);
        model.addAttribute("person", person);
        return "jstl/if";
    }

    @RequestMapping(value = "jstl/for")
    public String getForExample(Model model) {
        List<Map<String, Object>> persons = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++) {
            Map<String, Object> person = new HashMap<>();
            person.put("name", "윤영권");
            person.put("married", true);
            person.put("hasChildren", true);
            persons.add(person);
        }

        model.addAttribute("persons", persons);
        return "jstl/for";
    }

    @RequestMapping(value = "jstl/fortoken")
    public String getForTokensExample(Model model) {
        String tokenString = "a,b,c,d,e,f,g,h";
        model.addAttribute("token", tokenString);
        return "jstl/fortoken";
    }

    @RequestMapping(value = "jstl/fmt")
    public String getFmtExample(Model model) {
        model.addAttribute("date", new Date());
        model.addAttribute("number", 12345678900.4);

        return "jstl/fmt";
    }

    @RequestMapping(value = "jstl/import")
    public String getImportExample() {
        return "jstl/import";
    }

    @RequestMapping(value = "jstl/except")
    public String getExceptExample(String value1, String value2, Model model) {
        model.addAttribute("value1", Integer.valueOf(value1));
        model.addAttribute("value2", Integer.valueOf(value2));
        return "jstl/except";
    }
}
