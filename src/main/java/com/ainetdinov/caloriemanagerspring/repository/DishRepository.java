package com.ainetdinov.caloriemanagerspring.repository;

import com.ainetdinov.caloriemanagerspring.model.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
}
