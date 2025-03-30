package com.ainetdinov.caloriemanagerspring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionHandler {
    @ExceptionHandler(NoSuchUserException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchUserException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(NonUniqueUserException.class)
    public ResponseEntity<String> handleNonUniqueUserException(NonUniqueUserException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidDateBoundaryException.class)
    public ResponseEntity<String> handleInvalidDateBoundaryException(InvalidDateBoundaryException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
