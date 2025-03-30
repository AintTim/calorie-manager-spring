package com.ainetdinov.caloriemanagerspring.model.entity;

import com.ainetdinov.caloriemanagerspring.model.dto.MealDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class DailyReport {
    private LocalDate date;
    private double calorieLimit;
    private double calories;
    private List<MealDto> meals;
    private boolean withinLimit;
}
