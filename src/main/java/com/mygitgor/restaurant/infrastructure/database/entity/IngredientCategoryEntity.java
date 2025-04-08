package com.mygitgor.restaurant.infrastructure.database.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "ingredient_category")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"ingredients"})
public class IngredientCategoryEntity extends BaseEntity{
    private String name;
    @JsonIgnore
    @ManyToOne
    private RestaurantEntity restaurant;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<IngredientItemEntity> ingredients = new ArrayList<>();
}
