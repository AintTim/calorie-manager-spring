package com.ainetdinov.caloriemanagerspring.service;

import com.ainetdinov.caloriemanagerspring.constant.Goal;
import com.ainetdinov.caloriemanagerspring.constant.Sex;
import com.ainetdinov.caloriemanagerspring.model.entity.Dish;
import com.ainetdinov.caloriemanagerspring.model.entity.Meal;
import com.ainetdinov.caloriemanagerspring.model.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BaseTest {

    protected User createUser(String email) {
        User user = new User();
        user.setName("test");
        user.setEmail(email);
        user.setAge(20);
        user.setWeight(70.0);
        user.setHeight(180.0);
        user.setSex(Sex.MALE);
        user.setGoal(Goal.WEIGHT_MAINTENANCE);
        return user;
    }

    protected List<Meal> createMeals(int count, User user) {
        List<Meal> meals = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Meal meal = Meal.builder()
                    .user(user)
                    .date(LocalDate.now())
                    .dishes(createDishes(3))
                    .calories(300.0)
                    .build();
            meals.add(meal);
        }
        return meals;
    }

    protected List<Dish> createDishes(int count) {
        List<Dish> dishes = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Dish dish = new Dish();
            dish.setName("dish" + i);
            dish.setCalories(100.0);
            dish.setCarbs(1.0);
            dish.setFats(1.0);
            dish.setProteins(1.0);
            dishes.add(dish);
        }
        return dishes;
    }
}
