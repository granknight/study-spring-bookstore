package me.xyzlast.web.controllers;

import me.xyzlast.bh.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ykyoon on 14. 3. 26.
 * Velocity를 이용한 Book List
 */
@Controller
public class VelocityBookListController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "velocity/book/list")
    public String getBookList(Model model) {
        model.addAttribute("books", bookService.listup());
        return "vm-books";
    }
}
