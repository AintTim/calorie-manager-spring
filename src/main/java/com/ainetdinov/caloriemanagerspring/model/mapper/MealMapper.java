package com.ainetdinov.caloriemanagerspring.model.mapper;

import com.ainetdinov.caloriemanagerspring.model.dto.MealDto;
import com.ainetdinov.caloriemanagerspring.model.entity.Meal;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MealMapper extends EntityMapper<Meal, MealDto> {

    MealMapper INSTANCE = Mappers.getMapper(MealMapper.class);

    @Override
    MealDto toDto(Meal entity);
}
