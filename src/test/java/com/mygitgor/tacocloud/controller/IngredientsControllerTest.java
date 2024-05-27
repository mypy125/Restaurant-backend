package com.mygitgor.tacocloud.controller;

import com.mygitgor.tacocloud.domain.IngredientCategory;
import com.mygitgor.tacocloud.domain.IngredientItem;
import com.mygitgor.tacocloud.request.IngredientCategoryRequest;
import com.mygitgor.tacocloud.request.IngredientRequest;
import com.mygitgor.tacocloud.service.IngredientService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class IngredientsControllerTest {
    @Mock
    private IngredientService ingredientService;

    @InjectMocks
    IngredientsController ingredientsController;

    @Test
    public void testCreateIngredientCategory()throws Exception{
        IngredientCategoryRequest request = new IngredientCategoryRequest();
        request.setName("Vegetables");
        request.setRestaurantId(1L);

        IngredientCategory category = new IngredientCategory();
        category.setName("Vegetables");
        when(ingredientService.createIngredientCategory(eq("Vegetables"), eq(1L))).thenReturn(category);

        ResponseEntity<IngredientCategory> response = ingredientsController.createIngredientCategory(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(category, response.getBody());
    }

    @Test
    public void testCreateIngredientItem()throws Exception{
        IngredientRequest request = new IngredientRequest();
        request.setName("Tomato");
        request.setRestaurantId(1L);
        request.setCategoryId(2L);

        IngredientItem item = new IngredientItem();
        item.setName("Tomato");

        when(ingredientService.createIngredientItem(eq(1L), eq("Tomato"), eq(2L))).thenReturn(item);

        ResponseEntity<IngredientItem> response = ingredientsController.createIngredientItem(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(item, response.getBody());
    }

}
