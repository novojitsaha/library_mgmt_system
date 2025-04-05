package com.crescendo.library.web;

import com.crescendo.library.model.Book;
import com.crescendo.library.service.LibraryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class LibraryController {


    @Autowired
    private LibraryService libraryService;


    /**
     * Homepage endpoint
     * @return Welcome banner text.
     */
    @GetMapping("/")
    public String index() {
        return "Welcome to Library Management System";
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

    /**
     * Returns the list of books in the dateabase.
     * @return A list of BookModel Objects.
     */
    @GetMapping("/books")
    public Iterable<Book> getAllBooks(){
        return libraryService.getAllBooks();
    }

    /**
     * Search for a book by its ID.
     * @param id the ID of the book to be searched.
     * @return The BookModel Object found in the database.
     * @throws ResponseStatusException 404 HTTP Status if book is not found.
     */
    @GetMapping("/books/{id}")
    public ResponseEntity<Object> getBookById(@PathVariable Long id){
        Optional<Book> book = libraryService.getBookById(id);

        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        } else {
            Map<String, Object> errorBody = new LinkedHashMap<>();
            errorBody.put("status", 404);
            errorBody.put("error", "Not Found");
            errorBody.put("message", "Book with ID " + id + " not found.");
            errorBody.put("timestamp", new Date());
            errorBody.put("path", "/books/" + id);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
        }
    }

    /**
     * Remove a book by its ID.
     * @param id the ID of the book to be removed
     */
    @DeleteMapping("/books/{id}")
    public void removeBookById(@PathVariable Long id){
        libraryService.removeBookById(id);

    }



}
