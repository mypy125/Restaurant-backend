package com.mygitgor.restaurant.api.controller.DTOs.request;

import com.mygitgor.restaurant.api.controller.DTOs.AddressDto;
import lombok.Data;

@Data
public class OrderRequest {
    private Long restaurantId;
    private AddressDto deliveryAddress;
}
