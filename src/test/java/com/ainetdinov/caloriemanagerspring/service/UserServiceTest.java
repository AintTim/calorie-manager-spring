package com.ainetdinov.caloriemanagerspring.service;

import com.ainetdinov.caloriemanagerspring.constant.Goal;
import com.ainetdinov.caloriemanagerspring.constant.Sex;
import com.ainetdinov.caloriemanagerspring.model.entity.User;
import com.ainetdinov.caloriemanagerspring.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceTest extends BaseTest {

    @Autowired
    UserService userService;

    @MockitoBean
    UserRepository userRepository;

    @ParameterizedTest
    @EnumSource(Goal.class)
    void createUser_ShouldCreateUserWithAdjustedDailyCalories(Goal goal) {
        User user = createUser("test@mail.ru");
        user.setGoal(goal);

        doReturn(user).when(userRepository).save(user);

        double expectedDailyCalories = calculateBMR(user);
        userService.createUser(user);
        assertEquals(expectedDailyCalories, user.getDailyCalories());
    }

    @Test
    void validateUserMailPresence_ShouldReturnTrue_WhenEmailIsTaken() {
        User user = createUser("test@mail.ru");

        doReturn(Optional.of(user)).when(userRepository).findUserByEmail(anyString());

        assertTrue(userService.validateUserMailPresence(user));
    }

    @Test
    void validateUserMailPresence_ShouldReturnFalse_WhenEmailIsAvailable() {
        User user = createUser("test@mail.ru");

        doReturn(Optional.empty()).when(userRepository).findUserByEmail(anyString());
        assertFalse(userService.validateUserMailPresence(user));
    }


    private double calculateBMR(User user) {
        Sex sex = user.getSex();
        double calories = sex.getRatio() + (sex.getWeight() * user.getWeight()) + (sex.getHeight() * user.getHeight()) - (sex.getAge() * user.getAge());
        return user.getGoal().getRatio() * calories;
    }
}