package com.ainetdinov.caloriemanagerspring.model.dto;

import com.ainetdinov.caloriemanagerspring.model.entity.Dish;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Value
public class MealDto implements Serializable, EntityDto {
    Long id;
    Double calories;
    LocalDate date;
    List<Dish> dishes;

}
