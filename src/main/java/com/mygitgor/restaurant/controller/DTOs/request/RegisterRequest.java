package com.mygitgor.restaurant.controller.DTOs.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @JsonProperty("fullname")
    private String fullName;
    private String email;
    private String password;
    private String role;
}
