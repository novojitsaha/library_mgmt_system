package com.crescendo.library.exception;

public class InvalidReturnRequestException extends RuntimeException {
    /**
     * When a book is already returned, and still a return request is made, this exception is thrown.
     * @param id The ID of the book with which this illegal return request was made.
     */
    public InvalidReturnRequestException(Long id) {
        super(String.format("Book with ID %d was never borrowed!", id));
    }
}
