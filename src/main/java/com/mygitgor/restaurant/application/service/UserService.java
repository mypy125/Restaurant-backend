package com.mygitgor.restaurant.application.service;

import com.mygitgor.restaurant.infrastructure.database.entity.UserEntity;
import com.mygitgor.restaurant.api.controller.DTOs.UserProfileDto;
import com.mygitgor.restaurant.model.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findUserByJwtToken(String jwt)throws Exception;
    User findUserByEmail(String email)throws Exception;
    void updateUserProfile(UserProfileDto userProfileDto, String jwt) throws Exception;
}
