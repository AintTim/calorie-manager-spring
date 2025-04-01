package com.ainetdinov.caloriemanagerspring.controller;

import com.ainetdinov.caloriemanagerspring.model.dto.MealDto;
import com.ainetdinov.caloriemanagerspring.model.mapper.MealMapper;
import com.ainetdinov.caloriemanagerspring.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static com.ainetdinov.caloriemanagerspring.constant.WebConstant.API;
import static com.ainetdinov.caloriemanagerspring.constant.WebConstant.MEALS;

@RestController
@RequestMapping(API + MEALS)
@RequiredArgsConstructor
public class MealController {
    private final MealService mealService;
    private final MealMapper mapper;

    @PostMapping
    public ResponseEntity<MealDto> createMeal(@RequestParam long userId,
                                              @RequestBody List<Long> dishes,
                                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate date) {
        if (Objects.isNull(date)) {
            date = LocalDate.now();
        }
        return ResponseEntity.ok(mapper.toDto(mealService.createMeal(userId, dishes, date)));
    }
}
