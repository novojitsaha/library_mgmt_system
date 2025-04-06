package com.crescendo.library.web;

import com.crescendo.library.exception.BookNotFoundException;
import com.crescendo.library.exception.SearchTypes;
import com.crescendo.library.model.Book;
import com.crescendo.library.service.LibraryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class LibraryControllerTest {

    @Mock
    private LibraryService libraryService;

    @InjectMocks
    private LibraryController libraryController;

    @Test
    public void testAddBook() {
        Book book = new Book(1L, "Title", "Author", true);
        when(libraryService.getBookById(1L)).thenReturn(book);
        ResponseEntity<Book> response = libraryController.getBookById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Title", response.getBody().getTitle());
        assertEquals("Author", response.getBody().getAuthor());
        assertEquals(true, response.getBody().getIsAvailable());

    }

    @Test
    public void testGetAllBooks() {
        Book book1 = new Book(1L, "Title1", "Author1", true);
        Book book2 = new Book(2L, "Title2", "Author2", true);
        Iterable<Book> books = List.of(book1, book2);

        when(libraryService.getAllBooks()).thenReturn(books);
        Iterable<Book> res = libraryController.getAllBooks();
        Iterator<Book> it = res.iterator();
        assertEquals("Title1", it.next().getTitle());
        assertEquals("Title2", it.next().getTitle());
    }

    @Test
    void shouldReturnBookWhenIdIsValid() {
        Book book = new Book(1L, "Title", "Author", true);
        when(libraryService.getBookById(1L)).thenReturn(book);

        ResponseEntity<Book> response = libraryController.getBookById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Title", response.getBody().getTitle());
    }

    @Test
    void shouldThrowBookNotFoundExceptionWhenIdIsInvalid() {
        when(libraryService.getBookById(99L))
                .thenThrow(new BookNotFoundException(SearchTypes.ID, 99L));

        assertThrows(BookNotFoundException.class, () -> {
            libraryController.getBookById(99L);
        });
    }



}
