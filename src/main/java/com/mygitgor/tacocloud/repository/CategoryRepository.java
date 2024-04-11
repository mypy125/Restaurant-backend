package com.mygitgor.tacocloud.repository;

import com.mygitgor.tacocloud.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> finByRestaurantId(Long id);
}
