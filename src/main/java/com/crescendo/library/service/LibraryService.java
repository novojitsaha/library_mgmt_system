package com.crescendo.library.service;

import com.crescendo.library.model.Book;
import com.crescendo.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LibraryService {

    private final BookRepository bookRepository;

    public LibraryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }


    public Iterable<Book> getBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }


    public Iterable<Book> getBookByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public void removeBookById(Long id) {
        bookRepository.deleteById(id);
    }




}
