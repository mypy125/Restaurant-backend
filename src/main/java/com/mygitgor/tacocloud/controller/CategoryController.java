package com.mygitgor.tacocloud.controller;

import com.mygitgor.tacocloud.domain.Category;
import com.mygitgor.tacocloud.domain.User;
import com.mygitgor.tacocloud.service.CategoryService;
import com.mygitgor.tacocloud.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CategoryController управляет операциями, связанными с категориями ресторанов.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final UserService userService;

    @SneakyThrows
    @PostMapping("/admin/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category,
                                                   @RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwtToken(jwt);

        Category createCategory = categoryService.createCategory(category.getName(), user.getId());

        return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
    }

    @SneakyThrows
    @GetMapping("/category/restaurant/{id}")
    public ResponseEntity<List<Category>> getRestaurantCategory(@PathVariable Long id,
                                                                @RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwtToken(jwt);

        List<Category> getCategoryByRestaurantId = categoryService.findCategoryByRestaurantId(id);

        return new ResponseEntity<>(getCategoryByRestaurantId, HttpStatus.CREATED);
    }

}
