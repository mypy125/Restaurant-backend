package com.mygitgor.restaurant.infrastructure.database.impl;

import com.mygitgor.restaurant.infrastructure.database.entity.CategoryEntity;
import com.mygitgor.restaurant.infrastructure.database.jpa.CategoryJpaRepository;
import com.mygitgor.restaurant.mapper.CategoryMapper;
import com.mygitgor.restaurant.model.domain.Category;
import com.mygitgor.restaurant.model.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {
    private final CategoryJpaRepository jpaRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Category save(Category category) {
        CategoryEntity entity = categoryMapper.toEntity(category);
        return categoryMapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<Category> findById(Long id) {
        return jpaRepository.findById(id).map(categoryMapper::toDomain);
    }

    @Override
    public List<Category> findByRestaurantId(Long restaurantId) {
        return jpaRepository.findByRestaurantId(restaurantId)
                .stream()
                .map(categoryMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Category> findByName(String name) {
        return jpaRepository.findByName(name)
                .map(categoryMapper::toDomain);
    }
}
