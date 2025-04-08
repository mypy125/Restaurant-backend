package com.mygitgor.restaurant.infrastructure.database.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "food")
@NoArgsConstructor
@AllArgsConstructor
public class FoodEntity extends BaseEntity{
    private String name;
    private String description;
    private Long price;

    @ManyToOne
    private CategoryEntity foodCategory;

    @Column(length = 1000)
    @ElementCollection
    private List<String> images;

    private boolean available;

    @ManyToOne
    private RestaurantEntity restaurant;

    private boolean isVegetarian;
    private boolean isSeasonal;

    @ManyToMany
    @JsonManagedReference
    private List<IngredientItemEntity> ingredients = new ArrayList<>();

    private Date createondate;
}
