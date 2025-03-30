package com.ainetdinov.caloriemanagerspring.model.entity;

import com.ainetdinov.caloriemanagerspring.model.dto.MealDto;
import com.ainetdinov.caloriemanagerspring.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class ExtendedReport {
    private LocalDate start;
    private LocalDate end;
    private UserDto user;
    private Map<LocalDate, List<MealDto>> meals;
}
