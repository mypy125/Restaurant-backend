package com.mygitgor.tacocloud.controller;

import com.mygitgor.tacocloud.domain.IngredientCategory;
import com.mygitgor.tacocloud.domain.IngredientItem;
import com.mygitgor.tacocloud.request.IngredientCategoryRequest;
import com.mygitgor.tacocloud.request.IngredientRequest;
import com.mygitgor.tacocloud.service.IngredientService;
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

    @Test
    public void testGetRestaurantIngredient()throws Exception{
        Long restaurantId = 1L;
        IngredientItem item1 = new IngredientItem();
        item1.setName("Tomato");
        IngredientItem item2 = new IngredientItem();
        item2.setName("Lettuce");

        List<IngredientItem> items = Arrays.asList(item1, item2);

        when(ingredientService.findRestaurantIngredients(eq(restaurantId))).thenReturn(items);

        ResponseEntity<List<IngredientItem>>response = ingredientsController.getRestaurantIngredient(restaurantId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(items, response.getBody());
    }

    @Test
    public void testGetRestaurantIngredientCategory()throws Exception{
        Long restaurantId = 1L;
        IngredientCategory category1 = new IngredientCategory();
        category1.setName("Vegetables");
        IngredientCategory category2 = new IngredientCategory();
        category2.setName("Fruits");

        List<IngredientCategory>categories = Arrays.asList(category1, category2);

        when(ingredientService.findIngredientCategoryByRestaurantId(restaurantId)).thenReturn(categories);

        ResponseEntity<List<IngredientCategory>>response = ingredientsController.getRestaurantIngredientCategory(restaurantId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categories, response.getBody());
    }

}
