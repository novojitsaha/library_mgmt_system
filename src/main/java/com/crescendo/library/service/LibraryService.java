package com.crescendo.library.service;

import com.crescendo.library.exception.BookNotFoundException;
import com.crescendo.library.exception.InvalidBorrowRequestException;
import com.crescendo.library.exception.InvalidReturnRequestException;
import com.crescendo.library.exception.SearchTypes;
import com.crescendo.library.model.Book;
import com.crescendo.library.repository.BookRepository;
import org.springframework.stereotype.Service;


@Service
public class LibraryService {


    private final BookRepository bookRepository;

    public LibraryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Adds a book to the database
     * @param book The book to be added.
     * @return Returns the book that has been added with 200 OK Status.
     */
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    /**
     * Returns all the books from the database.
     * @return A list of all the books.
     */
    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Search for a book by its ID.
     * @param id The ID of the book to be searched.
     * @return Return the book if it exists. Else, BookNotFoundException is gracefully thrown and handled.
     */
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(SearchTypes.ID, id));
    }

    /**
     * Search for books by title.
     * @param title The title of the books to be searched.
     * @return A list of books with the given title.
     */
    public Iterable<Book> getBookByTitle(String title) {
        Iterable<Book> books = bookRepository.findByTitle(title);

        if(books.iterator().hasNext()) {
            return books;
        } else{
            throw new BookNotFoundException(SearchTypes.TITLE, title);
        }

    }

    /**
     * Search for books by author.
     * @param author The author of the books to be searched.
     * @return A list of books with the given author.
     */
    public Iterable<Book> getBookByAuthor(String author) {
        Iterable<Book> books = bookRepository.findByAuthor(author);

        if(books.iterator().hasNext()) {
            return books;
        } else{
            throw new BookNotFoundException(SearchTypes.AUTHOR, author);
        }
    }

    /**
     * Delete a book by its ID.
     * @param id The ID of the book to be deleted.
     */
    public void removeBookById(Long id) {
        bookRepository.deleteById(id);
    }

    /**
     * Delete all books with the given title.
     * @param title The title of the books to be deleted.
     */
    public void removeBookByTitle(String title) {
        bookRepository.deleteAll(bookRepository.findByTitle(title));
    }

    /**
     * Delete all books of the given author.
     * @param author The author of the books to be deleted.
     */
    public void removeBookByAuthor(String author) {
        bookRepository.deleteAll(bookRepository.findByAuthor(author));
    }

    /**
     * Borrow a book by its ID.
     * This is done by changing the isAvailable field of a book from true to false, given it was true initially.
     * In case the book was already borrowed, IllegalBorrowRequestException is gracefully thrown and handled.
     * @param query The ID of the book to be borrowed.
     */
    public void borrowBookById(Long query) {
        Book book = bookRepository.findById(query).orElseThrow(() -> new BookNotFoundException(SearchTypes.ID, query));

        if(!book.getIsAvailable()){
            throw new InvalidBorrowRequestException(book.getId());
        } else {
            book.setIsAvailable(false);
            bookRepository.save(book);
        }

    }

    /**
     * Return a book by its ID.
     * This is done by changing the isAvailable field of a book from false to true, given it was initially false.
     * In the case the book was already returned, IllegalReturnRequestException is gracefully thrown and handled.
     * @param query The ID of the book to be returned.
     */
    public void returnBookById(Long query) {
        Book book = bookRepository.findById(query).orElseThrow(() -> new BookNotFoundException(SearchTypes.ID, query));

        if(book.getIsAvailable()){
            throw new InvalidReturnRequestException(book.getId());
        } else {
            book.setIsAvailable(true);
            bookRepository.save(book);
        }
    }
}
