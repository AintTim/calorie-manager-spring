package com.ainetdinov.caloriemanagerspring.service;

import com.ainetdinov.caloriemanagerspring.model.dto.EntityDto;
import com.ainetdinov.caloriemanagerspring.model.entity.Source;
import com.ainetdinov.caloriemanagerspring.model.mapper.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class EntityService<S extends Source, D extends EntityDto, R extends JpaRepository<S, Long>, M extends EntityMapper<S, D>> {
    protected final R repository;
    protected final M mapper;

    public Optional<D> findById(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    public List<D> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public List<D> convertToDto(List<S> source) {
        return source.stream().map(mapper::toDto).toList();
    }
}
