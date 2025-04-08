package com.mygitgor.restaurant.application.impl;

import com.mygitgor.restaurant.infrastructure.database.entity.IngredientCategoryEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.IngredientItemEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.RestaurantEntity;
import com.mygitgor.restaurant.model.repository.IngredientCategoryRepository;
import com.mygitgor.restaurant.model.repository.IngredientItemRepository;
import com.mygitgor.restaurant.application.service.IngredientService;
import com.mygitgor.restaurant.application.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * IngredientService: этот сервиз относятся к ингредиентам.
 */
@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private final IngredientItemRepository ingredientItemRepository;
    private final IngredientCategoryRepository ingredientCategoryRepository;
    private final RestaurantService restaurantService;


    /**
     * метод createIngredientCategory предназначен для создания новой категории ингредиентов.
     * Он принимает имя новой категории (name) и идентификатор ресторана (restaurantId) в качестве параметров
     * @param name нозвания катгори
     * @param restaurantId идентификатор ресторана
     * @return возврошает ингредиент категория
     * @throws Exception  бросает исключения Exception
     */
    @Override
    public IngredientCategoryEntity createIngredientCategory(String name, Long restaurantId) throws Exception {
        RestaurantEntity restaurant = restaurantService.findRestaurantById(restaurantId);

        IngredientCategoryEntity category = new IngredientCategoryEntity();
        category.setRestaurant(restaurant);
        category.setName(name);

        return ingredientCategoryRepository.save(category);
    }

    /**
     *  метод служит для поиска категории ингредиентов по ее идентификатору (id).
     * @param id идентификатор категория
     * @return возврошает ингредиент категория
     * @throws Exception бросает исключения Exception
     */
    @Override
    public IngredientCategoryEntity findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientCategoryEntity> ingredientCategory = ingredientCategoryRepository.findById(id);

        if(ingredientCategory.isEmpty()){
            throw new Exception("ingredient category not found");
        }
        return ingredientCategory.get();
    }

    /**
     * метод предназначен для поиска категорий ингредиентов по идентификатору ресторана.
     * @param id идентификатор ресторана
     * @return возврошает список ингредиент категория
     * @throws Exception бросает исключения Exception
     */
    @Override
    public List<IngredientCategoryEntity> findIngredientCategoryByRestaurantId(Long id) throws Exception {
//        restaurantService.findRestaurantById(id);
        return ingredientCategoryRepository.findByRestaurantId(id);
    }

    /**
     * метод предназначен для создания нового ингредиента в указанной категории и ресторане.
     * @param restaurantId идентификатор ресторана
     * @param ingredientName нозвание ингредиента
     * @param categoryId идентификатор категории
     * @return возврашает созданый ингредиент итем
     * @throws Exception бросает исключения Exception
     */
    @Override
    public IngredientItemEntity createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception {
        RestaurantEntity restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategoryEntity category = findIngredientCategoryById(categoryId);

        IngredientItemEntity item = new IngredientItemEntity();
        item.setName(ingredientName);
        item.setRestaurant(restaurant);
        item.setCategory(category);

        IngredientItemEntity ingredient = ingredientItemRepository.save(item);
        category.getIngredients().add(ingredient);

        return ingredient;
    }

    /**
     * метод предназначен для поиска всех ингредиентов, связанных с определенным рестораном.
     * @param restaurantId идентификатор ресторана
     * @return возврошает список ингредиент итем указанного ресторана
     * @throws Exception бросает исключения Exception
     */
    @Override
    public List<IngredientItemEntity> findRestaurantIngredients(Long restaurantId) throws Exception {
        return ingredientItemRepository.findByRestaurantId(restaurantId);
    }

    /**
     *  метод предназначен для обновления состояния запасов ингредиента.
     * @param id идентификатор ингредиент итем
     * @return возврошает обновленый ингредиент итем
     * @throws Exception бросает исключения Exception
     */
    @Override
    public IngredientItemEntity updateStock(Long id) throws Exception {
       Optional<IngredientItemEntity> ingredientItem = ingredientItemRepository.findById(id);

       if(ingredientItem.isEmpty()){
           throw new Exception("ingredient not found");
       }

       IngredientItemEntity item = ingredientItem.get();
       item.setInStoke(!item.isInStoke());
       return ingredientItemRepository.save(item);
    }
}
