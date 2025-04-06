package com.crescendo.library.service;

import com.crescendo.library.exception.BookNotFoundException;
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


    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(SearchTypes.ID, id));
    }


    public Iterable<Book> getBookByTitle(String title) {
        Iterable<Book> books = bookRepository.findByTitle(title);

        if(books.iterator().hasNext()) {
            return books;
        } else{
            throw new BookNotFoundException(SearchTypes.TITLE, title);
        }

    }


    public Iterable<Book> getBookByAuthor(String author) {
        Iterable<Book> books = bookRepository.findByAuthor(author);

        if(books.iterator().hasNext()) {
            return books;
        } else{
            throw new BookNotFoundException(SearchTypes.AUTHOR, author);
        }
    }

    public void removeBookById(Long id) {
        bookRepository.deleteById(id);
    }




}
