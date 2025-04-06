package com.crescendo.library.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(SearchTypes searchType, Object value) {
        super(String.format("Book with %s %s cannot be found!", searchType.name().toLowerCase(), value.toString()));
    }
}
