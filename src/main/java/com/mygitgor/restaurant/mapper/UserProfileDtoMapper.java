package com.mygitgor.restaurant.mapper;

import com.mygitgor.restaurant.api.controller.DTOs.UserProfileDto;
import com.mygitgor.restaurant.model.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileDtoMapper {
    User toDomain(UserProfileDto userProfileDto);
    UserProfileDto toDto(User user);
}
