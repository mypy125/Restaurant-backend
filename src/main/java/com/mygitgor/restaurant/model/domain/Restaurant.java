package com.mygitgor.restaurant.model.domain;

import com.mygitgor.restaurant.infrastructure.database.entity.ContactInformation;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant extends BaseModelId{
    private Long ownerId;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<Order> orders;
    private List<String> images;
    private LocalDateTime registrationTime;
    private boolean open;
    private List<Food> foods;
}
