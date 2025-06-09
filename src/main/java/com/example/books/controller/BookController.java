package com.example.books.controller;

import com.example.books.domain.Book;
import com.example.books.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Book> getById(@PathVariable String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book) {
        Book created = bookService.create(book);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Book> update(@PathVariable String id, @RequestBody Book book) {
        Book updated = bookService.update(id, book);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updated);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Book> delete(@PathVariable String id) {
        bookService.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
    
}
