package com.mygitgor.restaurant.model.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseModelId{
    private String name;
    private Long restaurantId;
}
