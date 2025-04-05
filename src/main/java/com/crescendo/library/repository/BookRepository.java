package com.crescendo.library.repository;

import com.crescendo.library.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    /**
     *
     * @param title The title of the book to be searched.
     * @return An iterable of any books found.
     */
    Iterable<Book> findByTitle(String title);

    /**
     *
     * @param author The author of the book to be searched.
     * @return An iterable of any books found.
     */
    Iterable<Book> findByAuthor(String author);


}
