package com.crescendo.library.web;

import com.crescendo.library.model.Book;
import com.crescendo.library.service.LibraryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }


    /**
     * Add new book to the library.
     * @param book The book to be added.
     * @return book The book that has been added.
     */
    @Operation(
            summary = "Add a book to the database."
    )
    @PostMapping("/books")
    public Book addBook(@RequestBody @Valid Book book){

        return libraryService.addBook(book);

    }

    /**
     * Returns the list of books in the database.
     * @return A list of Book Objects.
     */
    @Operation(
            summary = "List all books."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Book[].class)
                    )
            )
    })
    @GetMapping("/books")
    public Iterable<Book> getAllBooks(){
        return libraryService.getAllBooks();
    }

    /**
     * Search for a book by its ID.
     * @param query The ID to be searched.
     * @return If the book is found, it's returned with 200 OK status code. Else, 404 status code returned with book not found message.
     */
    @Operation(
            summary = "Search a book by its ID."
    )
    @GetMapping("/books/search/id")
    public ResponseEntity<Book> getBookById(@RequestParam Long query){
        return ResponseEntity.ok(libraryService.getBookById(query));

    }

    /**
     * Search for a book by its title.
     * @param query The title of the book to be searched.
     * @return A list of all the books with the query title. If no such books are found, status code 404 is returned.
     */
    @Operation(
            summary = "Search for books by title."
    )
    @GetMapping("/books/search/title")
    public ResponseEntity<Object> getBookByTitle(@RequestParam String query){
        return ResponseEntity.ok(libraryService.getBookByTitle(query));
    }

    /**
     * Search for a book by its author.
     * @param query The author of the book to be searched.
     * @return The list of books with the query author.If no such books are found, return 404 status code.
     */
    @Operation(
            summary = "Search for books by author."
    )
    @GetMapping("/books/search/author")
    public ResponseEntity<Object> getBookByAuthor(@RequestParam String query){
        return ResponseEntity.ok(libraryService.getBookByAuthor(query));
    }


    /**
     * Remove a book by its ID.
     * @param query The ID of the book to be removed.
     */
    @Operation(
            summary = "Remove a book by its ID."
    )
    @DeleteMapping("/books/delete/id")
    public void removeBookById(@RequestParam Long query){
        libraryService.removeBookById(query);

    }

    /**
     * Remove all books with the given title.
     * @param query The title of the books to be removed.
     */
    @Operation(
            summary = "Remove all books with a given title."
    )
    @DeleteMapping("/books/delete/title")
    public void removeBookByTitle(@RequestParam String query){
        libraryService.removeBookByTitle(query);

    }

    /**
     * Remove all books of the given author.
     * @param query The author of the books to be removed.
     */
    @Operation(
            summary = "Remove all books of a given author."
    )
    @DeleteMapping("/books/delete/author")
    public void removeBookByAuthor(@RequestParam String query){
        libraryService.removeBookByAuthor(query);

    }

    /**
     * Borrow a book from the library.
     * @param query The ID of the book to be borrowed.
     * @return If borrowing is successful, 200 OK status with success message is returned. Otherwise 400 BAD REQUEST with error message is returned.
     */
    @Operation(
            summary = "Borrow a book by its ID."
    )
    @PatchMapping("/books/borrow/id")
    public ResponseEntity<Object> borrowBookById(@RequestParam Long query){
        libraryService.borrowBookById(query);
        return ResponseEntity.ok(String.format("Book with ID %d borrowed successfully!", query));
    }

    /**
     * Return a book to the library.
     * @param query The ID of the book to be returned.
     * @return If the book return is successful, 200 OK Status code is returned with success message. Otherwise 400 BAD REQUEST with error message is returned.
     */
    @Operation(
            summary = "Return a book by its ID."
    )
    @PatchMapping("/books/return/id")
    public ResponseEntity<Object> returnBookById(@RequestParam Long query){
        libraryService.returnBookById(query);
        return ResponseEntity.ok(String.format("Book with ID %d returned successfully!", query));
    }


}
