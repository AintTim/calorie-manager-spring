package com.ainetdinov.caloriemanagerspring.exception;

public class NoSuchUserException extends RuntimeException {
    public NoSuchUserException(Long id) {
        super(String.format("User %d not found", id));
    }
}
