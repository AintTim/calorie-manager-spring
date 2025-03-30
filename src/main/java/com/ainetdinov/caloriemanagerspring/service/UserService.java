package com.ainetdinov.caloriemanagerspring.service;

import com.ainetdinov.caloriemanagerspring.constant.Sex;
import com.ainetdinov.caloriemanagerspring.model.dto.UserDto;
import com.ainetdinov.caloriemanagerspring.model.entity.User;
import com.ainetdinov.caloriemanagerspring.model.mapper.UserMapper;
import com.ainetdinov.caloriemanagerspring.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends EntityService<User, UserDto, UserRepository, UserMapper> {

    public UserService(UserRepository userRepository) {
        super(userRepository, UserMapper.INSTANCE);
    }

    public boolean validateUserMailPresence(User user) {
        return repository.findUserByEmail(user.getEmail()).isPresent();
    }

    public UserDto createUser(User user) {
        double adjustedBmr = calculateBMR(user);
        user.setDailyCalories(adjustedBmr);
        return mapper.toDto(repository.save(user));
    }

    private double calculateBMR(User user) {
        Sex sex = user.getSex();
        double calories = sex.getRatio() + (sex.getWeight() * user.getWeight()) + (sex.getHeight() * user.getHeight()) - (sex.getAge() * user.getAge());
        return user.getGoal().getRatio() * calories;
    }
}
