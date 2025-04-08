package com.mygitgor.restaurant.api.controller.DTOs;

import lombok.Data;

@Data
public class AddressDto {
    private String streetAddress;
    private String city;
    private String stateProvince;
    private String postalCode;
    private String country;
}
