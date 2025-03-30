package com.ainetdinov.caloriemanagerspring.exception;

import java.time.LocalDate;

public class InvalidDateBoundaryException extends RuntimeException {
    public InvalidDateBoundaryException(LocalDate startDate, LocalDate endDate) {
        super(String.format("Start date %s is after end date %s", startDate, endDate));
    }
}
