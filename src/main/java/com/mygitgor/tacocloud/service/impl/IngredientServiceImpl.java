package com.mygitgor.tacocloud.service.impl;

import com.mygitgor.tacocloud.domain.IngredientCategory;
import com.mygitgor.tacocloud.domain.IngredientItem;
import com.mygitgor.tacocloud.domain.Restaurant;
import com.mygitgor.tacocloud.repository.IngredientCategoryRepository;
import com.mygitgor.tacocloud.repository.IngredientItemRepository;
import com.mygitgor.tacocloud.service.IngredientService;
import com.mygitgor.tacocloud.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private final IngredientItemRepository ingredientItemRepository;
    private final IngredientCategoryRepository ingredientCategoryRepository;
    private final RestaurantService restaurantService;


    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);

        IngredientCategory category = new IngredientCategory();
        category.setRestaurant(restaurant);
        category.setName(name);

        return ingredientCategoryRepository.save(category);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> ingredientCategory = ingredientCategoryRepository.findById(id);

        if(ingredientCategory.isEmpty()){
            throw new Exception("ingredient category not found");
        }
        return ingredientCategory.get();
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
        restaurantService.findRestaurantById(id);
        return ingredientCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory category = findIngredientCategoryById(categoryId);

        IngredientItem item = new IngredientItem();
        item.setName(ingredientName);
        item.setRestaurant(restaurant);
        item.setCategory(category);

        IngredientItem ingredient = ingredientItemRepository.save(item);
        category.getIngredients().add(ingredient);

        return ingredient;
    }

    @Override
    public List<IngredientItem> findRestaurantIngredients(Long restaurantId) throws Exception {
        return ingredientItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientItem updateStock(Long id) throws Exception {
       Optional<IngredientItem> ingredientItem = ingredientItemRepository.findById(id);

       if(ingredientItem.isEmpty()){
           throw new Exception("ingredient not found");
       }
       return ingredientItem.get();
    }
}
