package com.mygitgor.restaurant.controller.DTOs.request;

import lombok.Data;

@Data
public class UpdateCartItemRequest {
    private Long id;
    private int quantity;
}
