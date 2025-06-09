package com.example.books.controller;

import com.example.books.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest req) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ProblemDetail problem = ProblemDetail.forStatus(status);
        problem.setTitle("Resource Not Found");
        problem.setDetail(ex.getMessage());
        problem.setInstance(URI.create(req.getDescription(false).replace("uri=", "")));

        return ResponseEntity.status(status).body(problem);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleGenericException(Exception ex, WebRequest req) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ProblemDetail problem = ProblemDetail.forStatus(status);
        problem.setTitle("Internal Server Error");
        problem.setDetail("An unexpected error occurred: " + ex.getMessage());
        problem.setInstance(URI.create(req.getDescription(false).replace("uri=", "")));

        return ResponseEntity.status(status).body(problem);
    }

}
