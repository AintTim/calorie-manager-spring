package com.ainetdinov.caloriemanagerspring.model.mapper;

import com.ainetdinov.caloriemanagerspring.model.dto.UserDto;
import com.ainetdinov.caloriemanagerspring.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<User, UserDto> {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Override
    UserDto toDto(User entity);
}
