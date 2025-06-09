package com.example.books.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Class clazz, String field, String value) {
        super(String.format("%s not found with %s: %s", clazz.getSimpleName(), field, value));
    }
}
