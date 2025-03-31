package com.ainetdinov.caloriemanagerspring.service;

import com.ainetdinov.caloriemanagerspring.exception.NoSuchUserException;
import com.ainetdinov.caloriemanagerspring.model.dto.MealDto;
import com.ainetdinov.caloriemanagerspring.model.dto.UserDto;
import com.ainetdinov.caloriemanagerspring.model.entity.Dish;
import com.ainetdinov.caloriemanagerspring.model.entity.ExtendedReport;
import com.ainetdinov.caloriemanagerspring.model.entity.Meal;
import com.ainetdinov.caloriemanagerspring.model.entity.User;
import com.ainetdinov.caloriemanagerspring.repository.DishRepository;
import com.ainetdinov.caloriemanagerspring.repository.MealRepository;
import com.ainetdinov.caloriemanagerspring.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class MealServiceTest extends BaseTest {
    @Autowired
    MealService mealService;

    @MockitoBean
    MealRepository mealRepository;

    @MockitoBean
    UserRepository userRepository;

    @MockitoBean
    DishRepository dishRepository;

    @Test
    void createMeal_ShouldCreateCorrectMeal() {
        User user = createUser("test@mail.ru");
        List<Dish> dishes = createDishes(3);

        doReturn(Optional.of(user)).when(userRepository).findById(anyLong());
        doReturn(dishes).when(dishRepository).findAllById(any());

        mealService.createMeal(1L, List.of(1L, 2L), LocalDate.now());
        ArgumentCaptor<Meal> mealArgumentCaptor = ArgumentCaptor.forClass(Meal.class);
        verify(mealRepository).save(mealArgumentCaptor.capture());

        Meal expectedMeal = Meal.builder().user(user).dishes(dishes).date(LocalDate.now()).calories(300.0).build();
        Meal actualMeal = mealArgumentCaptor.getValue();
        assertThat(actualMeal).isEqualTo(expectedMeal);
    }

    @Test
    void createMeal_ShouldThrowException_WhenUserNotFound() {
        doReturn(Optional.empty()).when(userRepository).findById(anyLong());
        assertThrows(NoSuchUserException.class, () -> mealService.createMeal(1L, List.of(1L, 2L), LocalDate.now()));
    }

    @Test
    void getDailyReport_ShouldSetWithinLimitTrue_WhenEnoughCalories() {
        User user = createUser("test@mail.ru");
        user.setDailyCalories(500.0);
        List<Meal> meals = createMeals(1, user);

        doReturn(Optional.of(user)).when(userRepository).findById(anyLong());
        doReturn(meals).when(mealRepository).findByDateAndUserId(any(), anyLong());
        assertTrue(mealService.getDailyReport(1L, LocalDate.now()).isWithinLimit());
    }

    @Test
    void getDailyReport_ShouldSetWithinLimitFalse_WhenCaloriesExceedLimit() {
        User user = createUser("test@mail.ru");
        user.setDailyCalories(100.0);
        List<Meal> meals = createMeals(2, user);

        doReturn(Optional.of(user)).when(userRepository).findById(anyLong());
        doReturn(meals).when(mealRepository).findByDateAndUserId(any(), anyLong());
        assertFalse(mealService.getDailyReport(1L, LocalDate.now()).isWithinLimit());
    }

    @Test
    void getDailyReport_ShouldThrowException_WhenUserNotFound() {
        doReturn(Optional.empty()).when(userRepository).findById(anyLong());
        assertThrows(NoSuchUserException.class, () -> mealService.getDailyReport(1L, LocalDate.now()));
    }

    @Test
    void getExtendedReport_ShouldReturnCorrectReport() {
        UserDto user = new UserDto(1L, "test", "test@mail.ru");
        List<Meal> expectedMeals = createMeals(3, createUser("test@mail.ru"));
        doReturn(expectedMeals).when(mealRepository).findByUserIdAndDateBetween(anyLong(), any(), any());
        List<MealDto> actualMeals = mealService.getExtendedReport(user, LocalDate.now(), LocalDate.now()).getMeals().get(LocalDate.now());
        assertEquals(mealService.convertToDto(expectedMeals), actualMeals);
    }
}