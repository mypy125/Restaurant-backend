package com.mygitgor.restaurant.controller.DTOs.request;

import com.mygitgor.restaurant.dto.AddressDto;
import lombok.Data;

@Data
public class OrderRequest {
    private Long restaurantId;
    private AddressDto deliveryAddress;
}
