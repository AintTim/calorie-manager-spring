package com.ainetdinov.caloriemanagerspring.service;

import com.ainetdinov.caloriemanagerspring.exception.NoSuchUserException;
import com.ainetdinov.caloriemanagerspring.model.dto.MealDto;
import com.ainetdinov.caloriemanagerspring.model.dto.UserDto;
import com.ainetdinov.caloriemanagerspring.model.entity.*;
import com.ainetdinov.caloriemanagerspring.model.mapper.MealMapper;
import com.ainetdinov.caloriemanagerspring.repository.DishRepository;
import com.ainetdinov.caloriemanagerspring.repository.MealRepository;
import com.ainetdinov.caloriemanagerspring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MealService extends EntityService<Meal, MealDto, MealRepository, MealMapper> {
    private final UserRepository userRepository;
    private final DishRepository dishRepository;

    public MealService(MealRepository mealRepository, UserRepository userRepository, DishRepository dishRepository) {
        super(mealRepository, MealMapper.INSTANCE);
        this.userRepository = userRepository;
        this.dishRepository = dishRepository;
    }

    public Meal createMeal(Long userId, List<Long> dishIds, LocalDate date) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchUserException(userId));
        List<Dish> dishes = dishRepository.findAllById(dishIds);
        double totalCalories = calculateCalories(dishes);
        Meal meal = Meal.builder().user(user).dishes(dishes).calories(totalCalories).date(date).build();
        return repository.save(meal);
    }

    public DailyReport getDailyReport(Long userId, LocalDate date) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchUserException(userId));
        List<Meal> meals = repository.findByDateAndUserId(date, userId);
        double totalCalories = meals.stream()
                .mapToDouble(Meal::getCalories)
                .sum();
        boolean withinLimit = totalCalories <= user.getDailyCalories();
        return new DailyReport(date, user.getDailyCalories(), totalCalories, convertToDto(meals), withinLimit);
    }

    public ExtendedReport getExtendedReport(UserDto user, LocalDate start, LocalDate end) {
        List<MealDto> meals = convertToDto(repository.findByUserIdAndDateBetween(user.getId(), start, end));
        Map<LocalDate, List<MealDto>> dailyMeals = meals
                .stream()
                .collect(Collectors.groupingBy(MealDto::getDate));
        return new ExtendedReport(start, end, user, dailyMeals);
    }

    private double calculateCalories(List<Dish> dishes) {
        return dishes.stream()
                .mapToDouble(Dish::getCalories)
                .sum();
    }
}
