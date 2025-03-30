package com.ainetdinov.caloriemanagerspring.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Entity
@Table(name = "dishes")
public class Dish implements Source{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique = true)
    private String name;
    @Positive
    private Double calories;
    @PositiveOrZero
    private Double proteins;
    @PositiveOrZero
    private Double fats;
    @PositiveOrZero
    private Double carbs;
}
