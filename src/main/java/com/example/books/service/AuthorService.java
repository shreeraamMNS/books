package com.example.books.service;

import com.example.books.domain.Author;
import com.example.books.exception.ResourceNotFoundException;
import com.example.books.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    public Author getById(String id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Author.class, "id", id));
    }

    public Author create(Author author) {
        return authorRepository.save(author);
    }

    public Author update(String id, Author updated) {
        Author existing = getById(id);
        existing.setName(updated.getName());
        return authorRepository.save(existing);
    }

    public void delete(String id) {
        authorRepository.deleteById(id);
    }

}
