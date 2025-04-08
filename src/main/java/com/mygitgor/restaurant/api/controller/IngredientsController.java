package com.mygitgor.restaurant.api.controller;

import com.mygitgor.restaurant.infrastructure.database.entity.IngredientCategoryEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.IngredientItemEntity;
import com.mygitgor.restaurant.api.controller.DTOs.request.IngredientCategoryRequest;
import com.mygitgor.restaurant.api.controller.DTOs.request.IngredientRequest;
import com.mygitgor.restaurant.application.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * IngredientsController управляет операциями, связанными с ингредиентами для ресторанов.
 */
@RestController
@RequestMapping("/api/admin/ingredients")
@RequiredArgsConstructor
public class IngredientsController {
    private final IngredientService ingredientService;

    @PostMapping("/category")
    public ResponseEntity<IngredientCategoryEntity> createIngredientCategory(@RequestBody IngredientCategoryRequest request) throws Exception {
        IngredientCategoryEntity item = ingredientService.createIngredientCategory(request.getName(), request.getRestaurantId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<IngredientItemEntity> createIngredientItem(@RequestBody IngredientRequest request) throws Exception {
        IngredientItemEntity item = ingredientService.createIngredientItem(request.getRestaurantId(), request.getName(), request.getCategoryId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientItemEntity>> getRestaurantIngredient(@PathVariable Long id) throws Exception {
        List<IngredientItemEntity> items = ingredientService.findRestaurantIngredients(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategoryEntity>> getRestaurantIngredientCategory(@PathVariable Long id) throws Exception {
        List<IngredientCategoryEntity> items = ingredientService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PutMapping("/{id}/stoke")
    public ResponseEntity<IngredientItemEntity> updateIngredientStoke(@PathVariable Long id) throws Exception {
        IngredientItemEntity items = ingredientService.updateStock(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}
