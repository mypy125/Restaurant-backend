package com.mygitgor.restaurant.mapper;

import com.mygitgor.restaurant.infrastructure.database.entity.FoodEntity;
import com.mygitgor.restaurant.model.domain.Food;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",uses = {
        RestaurantMapper.class,
        CategoryMapper.class
})
public interface FoodMapper extends ConfigMapper<Food, FoodEntity> {
    FoodMapper INSTANCE = Mappers.getMapper(FoodMapper.class);
}
