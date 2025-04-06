package com.crescendo.library.exception;

public class InvalidReturnRequestException extends RuntimeException {
    public InvalidReturnRequestException(Long id) {
        super(String.format("Book with ID %d was never borrowed!", id));
    }
}
