package com.mygitgor.restaurant.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
@Embeddable
public class RestaurantDto {
    private Long id;
    private String name;
    private String title;
    private String description;

    @Column(length = 1000)
    private List<String> images;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestaurantDto that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
