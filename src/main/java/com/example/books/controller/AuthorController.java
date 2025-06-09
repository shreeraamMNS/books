package com.example.books.controller;

import com.example.books.domain.Author;
import com.example.books.service.AuthorService;
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
@RequestMapping(path = "/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authorService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Author> getById(@PathVariable String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authorService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Author> create(@RequestBody Author author) {
        Author created = authorService.create(author);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Author> update(@PathVariable String id, @RequestBody Author author) {
        Author updated = authorService.update(id, author);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updated);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Author> delete(@PathVariable String id) {
        authorService.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
