package com.mygitgor.tacocloud.request;

import com.mygitgor.tacocloud.domain.Address;
import com.mygitgor.tacocloud.domain.ContactInformation;
import lombok.Data;

import java.util.List;

@Data
public class CreateRestaurantRequest {
    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;
}
