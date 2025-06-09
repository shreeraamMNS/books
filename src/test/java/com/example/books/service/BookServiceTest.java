package com.example.books.service;

import com.example.books.domain.Book;
import com.example.books.exception.ResourceNotFoundException;
import com.example.books.repository.AuthorRepository;
import com.example.books.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void testCreateBookWithValidAuthor() {
        String authorId = "12345";
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthorId(authorId);

        when(authorRepository.existsById(authorId)).thenReturn(true);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book result = bookService.create(book);
        assertEquals("Test Book", result.getTitle());
    }

    @Test
    void testCreateBookWithInvalidAuthorThrowsException() {
        String authorId = "invalid";
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthorId(authorId);

        when(authorRepository.existsById(authorId)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> bookService.create(book));
    }

    @Test
    void testGetBookById() {
        Book book = new Book();
        book.setId("12345");
        book.setTitle("Test Book Id");
        book.setAuthorId("67890");

        when(bookRepository.findById("12345")).thenReturn(Optional.of(book));

        Book result = bookService.getById("12345");
        assertEquals("Test Book Id", result.getTitle());
        assertEquals("67890", result.getAuthorId());
    }

}
