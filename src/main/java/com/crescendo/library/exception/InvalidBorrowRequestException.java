package com.crescendo.library.exception;

public class InvalidBorrowRequestException extends RuntimeException {
  /**
   * When a borrow request is made for a book that's already been borrowed, this exception is thrown.
   * @param id The ID of the book with which this borrow request was made.
   */
  public InvalidBorrowRequestException(Long id) {
    super(String.format("Book with ID %d is already borrowed!", id));
  }
}
