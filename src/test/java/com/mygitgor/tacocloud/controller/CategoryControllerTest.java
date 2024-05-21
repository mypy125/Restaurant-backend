package com.mygitgor.tacocloud.controller;

import com.mygitgor.tacocloud.domain.Category;
import com.mygitgor.tacocloud.domain.User;
import com.mygitgor.tacocloud.service.CategoryService;
import com.mygitgor.tacocloud.service.UserService;
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
        Category category = new Category();
        category.setName("Sushi");

        User user = new User();
        user.setId(1L);

        Category createdCategory = new Category();
        createdCategory.setName("Sushi");

        when(userService.findUserByJwtToken(anyString())).thenReturn(user);
        when(categoryService.createCategory(eq("Sushi"), eq(1L))).thenReturn(createdCategory);
        ResponseEntity<Category> response = categoryController.createCategory(category, "dummy_jwt_token");
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdCategory, response.getBody());
    }

    @Test
    public void testGetRestaurantCategory() throws Exception{
        User user = new User();
        user.setId(1L);

        Category category1 = new Category();
        category1.setName("Sushi");
        Category category2 = new Category();
        category2.setName("Pizza");

        List<Category> categories = Arrays.asList(category1, category2);
        when(userService.findUserByJwtToken(anyString())).thenReturn(user);
        when(categoryService.findCategoryByRestaurantId(eq(1L))).thenReturn(categories);

        ResponseEntity<List<Category>> response = categoryController.getRestaurantCategory("dummy_jwt_token");

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(categories, response.getBody());
    }
}
