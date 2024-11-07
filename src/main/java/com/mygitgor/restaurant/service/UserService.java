package com.mygitgor.restaurant.service;

import com.mygitgor.restaurant.domain.User;
import com.mygitgor.restaurant.dto.UserProfileDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findUserByJwtToken(String jwt)throws Exception;
    User findUserByEmail(String email)throws Exception;
    void updateUserProfile(UserProfileDto userProfileDto, String jwt) throws Exception;

}
