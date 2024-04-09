package com.mygitgor.tacocloud.repository;

import com.mygitgor.tacocloud.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Restaurant findByOwnerId(Long id);

    @Query("SELECT r FROM Restaurant r " +
            "WHERE lower(r.name) LIKE lower(count ('%',:query, '%')) " +
            "OR lower(r.cuisineType) LIKE lower(concat('%',:query, '%'))")
    List<Restaurant> findBySearchQuery(String query);
}
