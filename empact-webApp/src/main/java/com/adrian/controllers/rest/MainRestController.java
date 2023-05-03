package com.adrian.controllers.rest;


import com.adrian.models.Book;
import com.adrian.services.WebAppServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class MainRestController {

    @Autowired
    private WebAppServices webAppServices;


    @GetMapping("/books")
    public Collection<Book> getAllBooks(){
        return webAppServices.findAllBooks();
    }

    @GetMapping(value = "/books/{id}")
    public String deleteBook(@PathVariable long id){
       webAppServices.delete(id);

       return "index";
    }


}
