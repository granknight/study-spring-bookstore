package me.xyzlast.web.controllers;

import me.xyzlast.bh.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "tiles/book/list")
    public String getTileExample1(Model model) {
        model.addAttribute("books", bookService.listup());
        return "tiles/book/list";
    }
}
