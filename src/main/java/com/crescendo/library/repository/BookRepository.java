package com.crescendo.library.repository;

import com.crescendo.library.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    /**
     * Find all books with a given title.
     * @param title The title of the books to be searched.
     * @return An iterable of any books found with the given title.
     */
    Iterable<Book> findByTitle(String title);

    /**
     * Find all books with a given author.
     * @param author The author of the books to be searched.
     * @return An iterable of any books found with the given author.
     */
    Iterable<Book> findByAuthor(String author);


}
