package com.crescendo.library.exception;

public class BookNotFoundException extends RuntimeException {
    /**
     * Custom Exception that is thrown when a book is searched for but not found.
     * @param searchType The parameter by which the book is searched by (id/title/author).
     * @param value The value of the search parameter.
     */
    public BookNotFoundException(SearchTypes searchType, Object value) {
        super(String.format("Book with %s %s cannot be found!", searchType.name().toLowerCase(), value.toString()));
    }
}
