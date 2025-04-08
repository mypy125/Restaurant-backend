package com.mygitgor.restaurant.infrastructure.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "ingredient_item")
@NoArgsConstructor
@AllArgsConstructor
public class IngredientItemEntity extends BaseEntity{
    private String name;

    @ManyToOne
    @JsonManagedReference
    private IngredientCategoryEntity category;

    @JsonIgnore
    @ManyToOne
    private RestaurantEntity restaurant;

    private boolean inStoke = true;
}
