package com.example.books.service;

import com.example.books.domain.Author;
import com.example.books.domain.Book;
import com.example.books.exception.ResourceNotFoundException;
import com.example.books.repository.AuthorRepository;
import com.example.books.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    public BookService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getById(String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Book.class, "id", id));
    }

    public Book create(Book book) {
        checkForAuthorExistence(book.getAuthorId());
        return bookRepository.save(book);
    }

    public Book update(String id, Book updated) {
        checkForAuthorExistence(updated.getAuthorId());
        Book existing = getById(id);
        existing.setTitle(updated.getTitle());
        existing.setAuthorId(updated.getAuthorId());

        return bookRepository.save(existing);
    }

    public void delete(String id) {
        bookRepository.deleteById(id);
    }

    private void checkForAuthorExistence(String authorId) {
        boolean isAuthorExists = authorRepository.existsById(authorId);
        if (!isAuthorExists) {
            throw new ResourceNotFoundException(Author.class, "id", authorId);
        }
    }

}
