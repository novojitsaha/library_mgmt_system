package com.crescendo.library.service;

import com.crescendo.library.model.Book;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class LibraryService {
    private Map<String, Book> db = new HashMap<>(){};


    public Collection<Book> getAllBooks() {
        return db.values();
    }

    public Book getBookById(String id) {
        return db.get(id);
    }

    public Book removeBookById(String id) {
        return db.remove(id);
    }

    public Book addBook(Book book) {
        book.setId(UUID.randomUUID().toString());
        db.put(book.getId(), book);
        return book;
    }
}
