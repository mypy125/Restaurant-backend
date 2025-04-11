package com.mygitgor.restaurant.model.domain;


import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Food extends BaseModelId{
    private String name;
    private String description;
    private Long price;
    private Long categoryId;
    private List<String> images;
    private boolean available;
    private Long restaurantId;
    private boolean isVegetarian;
    private boolean isSeasonal;
    private List<Long> ingredientIds;
    private Date createdOnDate;
}
