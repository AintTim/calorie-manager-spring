package com.ainetdinov.caloriemanagerspring.repository;

import com.ainetdinov.caloriemanagerspring.model.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {

    List<Meal> findByDateAndUserId(LocalDate date, long userId);

    List<Meal> findByUserIdAndDateBetween(long userId, LocalDate start, LocalDate end);
}
