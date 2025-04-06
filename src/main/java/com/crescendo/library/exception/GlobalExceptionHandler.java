package com.crescendo.library.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.crescendo.library.dto.ErrorResponse;
import java.util.Date;

/**
 * Class that defines handlers for the custom exceptions.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handler for the BookNotFoundException class.
     * @param ex The BookNotFoundException that it handles.
     * @param request The request that was made.
     * @return Response object with 404 Status Code and useful message.
     */
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBookNotFound(BookNotFoundException ex, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI(),
                new Date()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * Handler for the InvalidBorrowRequestException class.
     * @param ex The InvalidBorrowRequestException that it handles.
     * @param request The request that was made.
     * @return Response object with 400 Status Code and useful message.
     */
    @ExceptionHandler(InvalidBorrowRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidBorrowRequest(InvalidBorrowRequestException ex, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI(),
                new Date()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Handler for the InvalidReturnRequestException class.
     * @param ex The InvalidReturnRequestException that it handles.
     * @param request The request that was made.
     * @return Response object with 400 Status Code and useful message.
     */
    @ExceptionHandler(InvalidReturnRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidReturnRequest(InvalidReturnRequestException ex, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI(),
                new Date()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }



}
