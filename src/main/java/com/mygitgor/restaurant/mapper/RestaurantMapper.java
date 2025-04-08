package com.mygitgor.restaurant.mapper;

import com.mygitgor.restaurant.infrastructure.database.entity.RestaurantEntity;
import com.mygitgor.restaurant.model.domain.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RestaurantMapper extends ConfigMapper<Restaurant, RestaurantEntity> {
    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);
}
