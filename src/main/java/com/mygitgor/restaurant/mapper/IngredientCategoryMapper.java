package com.mygitgor.restaurant.mapper;

import com.mygitgor.restaurant.infrastructure.database.entity.IngredientCategoryEntity;
import com.mygitgor.restaurant.model.domain.IngredientCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IngredientCategoryMapper extends ConfigMapper<IngredientCategory, IngredientCategoryEntity> {
    IngredientCategoryMapper INSTANCE = Mappers.getMapper(IngredientCategoryMapper.class);
}
