package com.mygitgor.restaurant.api.controller.DTOs.response;

import lombok.Data;

@Data
public class MessageResponse {
    private String massage;

    public String getMessage() {
        return massage;
    }
}
