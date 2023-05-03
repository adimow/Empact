package com.adrian.services;

import com.adrian.dao.BookRepository;
import com.adrian.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class WebAppServices {

    @Autowired
    private BookRepository bookRepository;

    public Collection<Book> findAllBooks(){
        List<Book> books =new ArrayList<>();
        for (Book book : bookRepository.findAll()){
            books.add(book);
        }
        return books;
    }

    public void delete(Long id) {

          bookRepository.deleteById(id);
    }

    public Book findOne(Long id){

        return  bookRepository.getById(id);
    }

    public void save(Book book) {
        bookRepository.save(book);
    }




}
