package me.xyzlast.web.controllers;

import me.xyzlast.bh.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ykyoon on 3/18/14.
 */
@Controller
public class RythmBookListController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "rythm/books/list")
    public String getBookList(Model model) {
        model.addAttribute("books", bookService.listup());
        return "books";
    }
}
