package com.ainetdinov.caloriemanagerspring.exception;

public class NonUniqueUserException extends RuntimeException {
    public NonUniqueUserException(String mail) {
        super(String.format("Email '%s' is already taken!", mail));
    }
}
