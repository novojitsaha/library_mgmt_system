package com.crescendo.library.controller;

import com.crescendo.library.model.Book;
import com.crescendo.library.service.LibraryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
public class LibraryController {



    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }


    /**
     * Homepage endpoint
     * @return Welcome banner text.
     */
    @GetMapping("/")
    public String index() {
        return "Welcome to Library Management System";
    }

    /**
     * Returns the list of books in the dateabase.
     * @return A list of BookModel Objects.
     */
    @GetMapping("/books")
    public Collection<Book> getAllBooks(){
        return libraryService.getAllBooks();
    }

    /**
     * Search for a book by its ID.
     * @param id the ID of the book to be searched.
     * @return The BookModel Object found in the database.
     * @throws ResponseStatusException 404 HTTP Status if book is not found.
     */
    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable String id){
        Book book = libraryService.getBookById(id);

        if(book == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return book;
    }

    /**
     * Remove a book by its ID.
     * @param id the ID of the book to be removed
     */
    @DeleteMapping("/books/{id}")
    public void removeBookById(@PathVariable String id){
        Book book = libraryService.removeBookById(id);

        if(book == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    /**
     * Add new book to the library.
     * @param book The book to be added.
     * @return book The book that has been added.
     */
    @PostMapping("/books")
    public Book addBook(@RequestBody @Valid Book book){

        return libraryService.addBook(book);

    }

}
