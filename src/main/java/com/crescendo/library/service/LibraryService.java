package com.crescendo.library.service;

import com.crescendo.library.model.Book;
import com.crescendo.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LibraryService {

    @Autowired
    private BookRepository bookRepository;


    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {

        return bookRepository.findById(id);
    }

    public void removeBookById(Long id) {
        bookRepository.deleteById(id);
    }


}
