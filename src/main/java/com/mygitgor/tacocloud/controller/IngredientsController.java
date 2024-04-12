package com.mygitgor.tacocloud.controller;

import com.mygitgor.tacocloud.domain.IngredientCategory;
import com.mygitgor.tacocloud.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/ingredients")
@RequiredArgsConstructor
public class IngredientsController {
    private final IngredientService ingredientService;

    public ResponseEntity<IngredientCategory> createIngredientCategory(){
        return null;
    }
}
