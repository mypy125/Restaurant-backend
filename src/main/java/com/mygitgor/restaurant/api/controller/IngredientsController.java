package com.mygitgor.restaurant.api.controller;

import com.mygitgor.restaurant.api.controller.DTOs.request.IngredientCategoryRequest;
import com.mygitgor.restaurant.api.controller.DTOs.request.IngredientRequest;
import com.mygitgor.restaurant.application.service.IngredientService;
import com.mygitgor.restaurant.model.domain.IngredientCategory;
import com.mygitgor.restaurant.model.domain.IngredientItem;
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
    public ResponseEntity<IngredientCategory> createIngredientCategory(@RequestBody IngredientCategoryRequest request) throws Exception {
        IngredientCategory item = ingredientService.createIngredientCategory(request.getName(), request.getRestaurantId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<IngredientItem> createIngredientItem(@RequestBody IngredientRequest request) throws Exception {
        IngredientItem item = ingredientService.createIngredientItem(request.getRestaurantId(), request.getName(), request.getCategoryId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientItem>> getRestaurantIngredient(@PathVariable Long id) throws Exception {
        List<IngredientItem> items = ingredientService.findRestaurantIngredients(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategory(@PathVariable Long id) throws Exception {
        List<IngredientCategory> items = ingredientService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PutMapping("/{id}/stoke")
    public ResponseEntity<IngredientItem> updateIngredientStoke(@PathVariable Long id) throws Exception {
        IngredientItem items = ingredientService.updateStock(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}
