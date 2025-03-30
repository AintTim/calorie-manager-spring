package com.ainetdinov.caloriemanagerspring.controller;

import com.ainetdinov.caloriemanagerspring.model.entity.Dish;
import com.ainetdinov.caloriemanagerspring.repository.DishRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ainetdinov.caloriemanagerspring.constant.WebConstant.API;
import static com.ainetdinov.caloriemanagerspring.constant.WebConstant.DISHES;

@RestController
@RequestMapping(API + DISHES)
@RequiredArgsConstructor
public class DishController {
    private final DishRepository dishRepository;

    @PostMapping
    public ResponseEntity<Dish> createDish(@Valid @RequestBody Dish dish) {
        return new ResponseEntity<>(dishRepository.save(dish), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Dish>> getDishes() {
        return ResponseEntity.ok(dishRepository.findAll());
    }
}
