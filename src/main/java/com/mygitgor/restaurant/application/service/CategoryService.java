package com.mygitgor.restaurant.application.service;

import com.mygitgor.restaurant.infrastructure.database.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {

    CategoryEntity createCategory(String name, Long userId) throws Exception;
    List<CategoryEntity> findCategoryByRestaurantId(Long id)throws Exception;
    CategoryEntity findCategoryById(Long id)throws Exception;
}
