package com.mygitgor.restaurant.mapper;

import com.mygitgor.restaurant.infrastructure.database.entity.RestaurantEntity;
import com.mygitgor.restaurant.model.domain.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        uses = {
                AddressMapper.class,
                OrderMapper.class,
                FoodMapper.class
        })
public interface RestaurantMapper extends ConfigMapper<Restaurant, RestaurantEntity> {
    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);
    @Override
    @Mapping(source = "owner.id", target = "ownerId")
    Restaurant toDomain(RestaurantEntity entity);

    @Override
    @Mapping(source = "ownerId", target = "owner.id")
    RestaurantEntity toEntity(Restaurant restaurant);
}
