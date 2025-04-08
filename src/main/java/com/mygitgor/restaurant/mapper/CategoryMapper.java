package com.mygitgor.restaurant.mapper;

import com.mygitgor.restaurant.infrastructure.database.entity.CategoryEntity;
import com.mygitgor.restaurant.model.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends ConfigMapper<Category, CategoryEntity> {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
}
