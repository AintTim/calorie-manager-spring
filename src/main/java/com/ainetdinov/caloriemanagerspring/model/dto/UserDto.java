package com.ainetdinov.caloriemanagerspring.model.dto;

import lombok.Value;

import java.io.Serializable;

@Value
public class UserDto implements Serializable, EntityDto {
    Long id;
    String name;
    String email;
}
