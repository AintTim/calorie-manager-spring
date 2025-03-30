package com.ainetdinov.caloriemanagerspring.controller;

import com.ainetdinov.caloriemanagerspring.exception.NoSuchUserException;
import com.ainetdinov.caloriemanagerspring.exception.NonUniqueUserException;
import com.ainetdinov.caloriemanagerspring.model.dto.UserDto;
import com.ainetdinov.caloriemanagerspring.model.entity.User;
import com.ainetdinov.caloriemanagerspring.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ainetdinov.caloriemanagerspring.constant.WebConstant.API;
import static com.ainetdinov.caloriemanagerspring.constant.WebConstant.USERS;

@RestController
@RequestMapping(API + USERS)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody User user) {
        if (userService.validateUserMailPresence(user)) {
            throw new NonUniqueUserException(user.getEmail());
        }
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable long id) {
        var user = userService.findById(id);
        if (user.isEmpty()) {
            throw new NoSuchUserException(id);
        }
        return ResponseEntity.ok(user.get());
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        var users = userService.findAll();
        return ResponseEntity.ok(users);
    }
}
