package com.crescendo.library.exception;

public class InvalidBorrowRequestException extends RuntimeException {
  public InvalidBorrowRequestException(Long id) {
    super(String.format("Book with ID %d is already borrowed!", id));
  }
}
