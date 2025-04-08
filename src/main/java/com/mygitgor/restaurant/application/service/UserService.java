package com.mygitgor.restaurant.application.service;

import com.mygitgor.restaurant.infrastructure.database.entity.UserEntity;
import com.mygitgor.restaurant.api.controller.DTOs.UserProfileDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserEntity findUserByJwtToken(String jwt)throws Exception;
    UserEntity findUserByEmail(String email)throws Exception;
    void updateUserProfile(UserProfileDto userProfileDto, String jwt) throws Exception;

}
