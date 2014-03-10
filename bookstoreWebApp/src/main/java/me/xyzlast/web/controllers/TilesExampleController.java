package me.xyzlast.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ykyoon on 3/10/14.
 */
@Controller
public class TilesExampleController {

    @RequestMapping(value = "tiles/ex1")
    public String getTileExample1(Model model) {
        List<Map<String, Object>> persons = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++) {
            Map<String, Object> person = new HashMap<>();
            person.put("name", "윤영권");
            person.put("married", true);
            person.put("hasChildren", true);
            persons.add(person);
        }

        model.addAttribute("persons", persons);
        return "tiles/ex1";
    }
}
