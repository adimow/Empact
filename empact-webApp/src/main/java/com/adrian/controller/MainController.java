package com.adrian.controller;

import com.adrian.models.Book;
import com.adrian.services.WebAppServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class MainController {
    @Autowired
    private WebAppServices webAppServices;

    @GetMapping("/")
    public String init(HttpServletRequest req){
        req.setAttribute("books", webAppServices.findAllBooks());
        req.setAttribute("mode", "BOOK_VIEW");

        return "index";
    }

    @GetMapping("/editBook")
    public String init(@RequestParam long id, HttpServletRequest req){
        req.setAttribute("book", webAppServices.findOne(id));
        req.setAttribute("mode", "BOOK_EDIT");

        return "index";
    }



    @PostMapping("/save")
    public void save(@ModelAttribute Book book,  HttpServletRequest req, HttpServletResponse resp) throws IOException {
        webAppServices.save(book);
        req.setAttribute("books", webAppServices.findAllBooks());
        req.setAttribute("mode", "BOOK_VIEW");
        resp.sendRedirect("/");
    }

    @GetMapping("/add")
    public String newBook(HttpServletRequest req){
        req.setAttribute("mode", "BOOK_NEW");
        return "index";
    }

    @GetMapping("/deleteBook")
    public void deleteBook(@RequestParam long id, HttpServletRequest req, HttpServletResponse resp) throws IOException{
        webAppServices.delete(id);
        req.setAttribute("books", webAppServices.findAllBooks());
        req.setAttribute("mode", "BOOK_VIEW");
        resp.sendRedirect("/");
    }

}
