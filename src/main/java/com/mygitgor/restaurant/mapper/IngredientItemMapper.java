package com.mygitgor.restaurant.mapper;

import com.mygitgor.restaurant.infrastructure.database.entity.IngredientItemEntity;
import com.mygitgor.restaurant.model.domain.IngredientItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IngredientItemMapper extends ConfigMapper<IngredientItem, IngredientItemEntity> {
    IngredientItemMapper INSTANCE = Mappers.getMapper(IngredientItemMapper.class);
}
