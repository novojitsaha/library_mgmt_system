package com.crescendo.library.web;

import com.crescendo.library.model.Book;
import com.crescendo.library.service.LibraryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

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
     * Add new book to the library.
     * @param book The book to be added.
     * @return book The book that has been added.
     */
    @PostMapping("/books")
    public Book addBook(@RequestBody @Valid Book book){

        return libraryService.addBook(book);

    }

    /**
     * Returns the list of books in the database.
     * @return A list of BookModel Objects.
     */
    @GetMapping("/books")
    public Iterable<Book> getAllBooks(){
        return libraryService.getAllBooks();
    }


    @GetMapping("/books/search/id")
    public ResponseEntity<Object> getBookById(@RequestParam Long query){
        Optional<Book> book = libraryService.getBookById(query);
        if(book.isPresent()){
            return ResponseEntity.ok(book.get());
        } else{
            Map<String, Object> errorBody = new LinkedHashMap<>();
            errorBody.put("status", 404);
            errorBody.put("error", "Not Found");
            errorBody.put("message", "Book with id " + query + " not found.");
            errorBody.put("timestamp", new Date());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
        }
    }

    /**
     * Search for a book by its title.
     * @param query the title of the book to be searched.
     * @return The Book Object found in the database or 404 if no such book found.
     */
    @GetMapping("/books/search/title")
    public ResponseEntity<Object> getBookByTitle(@RequestParam String query){
        Iterable<Book> bookIterable = libraryService.getBookByTitle(query);

        if (bookIterable.iterator().hasNext()) {
            return ResponseEntity.ok(bookIterable);
        } else {
            Map<String, Object> errorBody = new LinkedHashMap<>();
            errorBody.put("status", 404);
            errorBody.put("error", "Not Found");
            errorBody.put("message", "Book with title " + query + " not found.");
            errorBody.put("timestamp", new Date());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
        }
    }

    /**
     * Search for a book by its title.
     * @param query the title of the book to be searched.
     * @return The Book Object found in the database or 404 if no such book found.
     */
    @GetMapping("/books/search/author")
    public ResponseEntity<Object> getBookByAuthor(@RequestParam String query){
        Iterable<Book> bookIterable = libraryService.getBookByAuthor(query);

        if (bookIterable.iterator().hasNext()) {
            return ResponseEntity.ok(bookIterable);
        } else {
            Map<String, Object> errorBody = new LinkedHashMap<>();
            errorBody.put("status", 404);
            errorBody.put("error", "Not Found");
            errorBody.put("message", "Book with author " + query + " not found.");
            errorBody.put("timestamp", new Date());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
        }
    }

    /**
     * Remove a book by its ID.
     * @param id the ID of the book to be removed
     */
    @DeleteMapping("/books/delete/id")
    public void removeBookById(@RequestParam Long id){
        libraryService.removeBookById(id);

    }



}
