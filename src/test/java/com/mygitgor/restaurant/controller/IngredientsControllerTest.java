package com.mygitgor.restaurant.controller;

import com.mygitgor.restaurant.api.controller.IngredientsController;
import com.mygitgor.restaurant.infrastructure.database.entity.IngredientCategoryEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.IngredientItemEntity;
import com.mygitgor.restaurant.api.controller.DTOs.request.IngredientCategoryRequest;
import com.mygitgor.restaurant.api.controller.DTOs.request.IngredientRequest;
import com.mygitgor.restaurant.application.service.IngredientService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
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

        IngredientCategoryEntity category = new IngredientCategoryEntity();
        category.setName("Vegetables");
        when(ingredientService.createIngredientCategory(eq("Vegetables"), eq(1L))).thenReturn(category);

        ResponseEntity<IngredientCategoryEntity> response = ingredientsController.createIngredientCategory(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(category, response.getBody());
    }

    @Test
    public void testCreateIngredientItem()throws Exception{
        IngredientRequest request = new IngredientRequest();
        request.setName("Tomato");
        request.setRestaurantId(1L);
        request.setCategoryId(2L);

        IngredientItemEntity item = new IngredientItemEntity();
        item.setName("Tomato");

        when(ingredientService.createIngredientItem(eq(1L), eq("Tomato"), eq(2L))).thenReturn(item);

        ResponseEntity<IngredientItemEntity> response = ingredientsController.createIngredientItem(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(item, response.getBody());
    }

    @Test
    public void testGetRestaurantIngredient()throws Exception{
        Long restaurantId = 1L;
        IngredientItemEntity item1 = new IngredientItemEntity();
        item1.setName("Tomato");
        IngredientItemEntity item2 = new IngredientItemEntity();
        item2.setName("Lettuce");

        List<IngredientItemEntity> items = Arrays.asList(item1, item2);

        when(ingredientService.findRestaurantIngredients(eq(restaurantId))).thenReturn(items);

        ResponseEntity<List<IngredientItemEntity>>response = ingredientsController.getRestaurantIngredient(restaurantId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(items, response.getBody());
    }

    @Test
    public void testGetRestaurantIngredientCategory()throws Exception{
        Long restaurantId = 1L;
        IngredientCategoryEntity category1 = new IngredientCategoryEntity();
        category1.setName("Vegetables");
        IngredientCategoryEntity category2 = new IngredientCategoryEntity();
        category2.setName("Fruits");

        List<IngredientCategoryEntity>categories = Arrays.asList(category1, category2);

        when(ingredientService.findIngredientCategoryByRestaurantId(restaurantId)).thenReturn(categories);

        ResponseEntity<List<IngredientCategoryEntity>>response = ingredientsController.getRestaurantIngredientCategory(restaurantId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categories, response.getBody());
    }

}
