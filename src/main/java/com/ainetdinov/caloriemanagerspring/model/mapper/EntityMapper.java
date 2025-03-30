package com.ainetdinov.caloriemanagerspring.model.mapper;

import com.ainetdinov.caloriemanagerspring.model.dto.EntityDto;
import com.ainetdinov.caloriemanagerspring.model.entity.Source;

public interface EntityMapper<E extends Source, D extends EntityDto> {

    D toDto(E entity);
}
