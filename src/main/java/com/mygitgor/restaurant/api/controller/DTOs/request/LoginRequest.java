package com.mygitgor.restaurant.api.controller.DTOs.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
