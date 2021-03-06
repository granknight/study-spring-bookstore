package me.xyzlast.web.controllers;

import me.xyzlast.bh.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ykyoon on 14. 3. 26.
 * Freemarker example
 */
@Controller
public class FreemarkerBookListController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "fm/book/list")
    public String getBookList(Model model) {
        model.addAttribute("books", bookService.listup());
        return "fm-books";
    }
}
