package com.example.books.service;

import com.example.books.domain.Author;
import com.example.books.exception.ResourceNotFoundException;
import com.example.books.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @Test
    void testCreateAuthor() {
        Author author = new Author();
        author.setName("John Doe");

        when(authorRepository.save(any(Author.class))).thenReturn(author);

        Author result = authorService.create(author);
        assertEquals("John Doe", result.getName());
    }

    @Test
    void testGetAuthorByIdFound() {
        String authorId = "12345";
        Author author = new Author();
        author.setId(authorId);
        author.setName("Jane");

        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));

        Author result = authorService.getById(authorId);
        assertEquals("Jane", result.getName());
    }

    @Test
    void testGetAuthorByIdNotFound() {
        String authorId = "invalid";

        when(authorRepository.findById(authorId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> authorService.getById(authorId));
    }

    @Test
    void testDeleteAuthor() {
        String authorId = "12345";

        doNothing().when(authorRepository).deleteById(authorId);

        authorService.delete("12345");
        verify(authorRepository, times(1)).deleteById(authorId);
    }

}
