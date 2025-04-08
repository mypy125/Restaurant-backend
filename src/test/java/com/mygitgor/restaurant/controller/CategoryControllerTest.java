package com.mygitgor.restaurant.controller;

import com.mygitgor.restaurant.api.controller.CategoryController;
import com.mygitgor.restaurant.infrastructure.database.entity.CategoryEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.UserEntity;
import com.mygitgor.restaurant.application.service.CategoryService;
import com.mygitgor.restaurant.application.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {
    @Mock
    private CategoryService categoryService;

    @Mock
    private UserService userService;

    @InjectMocks
    CategoryController categoryController;

    @Test
    public void testCreateCategory() throws Exception{
        CategoryEntity category = new CategoryEntity();
        category.setName("Sushi");

        UserEntity user = new UserEntity();
        user.setId(1L);

        CategoryEntity createdCategory = new CategoryEntity();
        createdCategory.setName("Sushi");

        when(userService.findUserByJwtToken(anyString())).thenReturn(user);
        when(categoryService.createCategory(eq("Sushi"), eq(1L))).thenReturn(createdCategory);
        ResponseEntity<CategoryEntity> response = categoryController.createCategory(category, "dummy_jwt_token");
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdCategory, response.getBody());
    }

//    @Test
//    public void testGetRestaurantCategory() throws Exception{
//        UserEntity user = new UserEntity();
//        user.setId(1L);
//
//        CategoryEntity category1 = new CategoryEntity();
//        category1.setName("Sushi");
//        CategoryEntity category2 = new CategoryEntity();
//        category2.setName("Pizza");
//
//        List<CategoryEntity> categories = Arrays.asList(category1, category2);
//        when(userService.findUserByJwtToken(anyString())).thenReturn(user);
//        when(categoryService.findCategoryByRestaurantId(eq(1L))).thenReturn(categories);
//
//        ResponseEntity<List<CategoryEntity>> response = categoryController.getRestaurantCategory("dummy_jwt_token");
//
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals(categories, response.getBody());
//    }
}
