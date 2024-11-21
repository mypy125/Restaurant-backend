package com.mygitgor.restaurant.service.impl;

import com.mygitgor.restaurant.domain.IngredientCategory;
import com.mygitgor.restaurant.domain.IngredientItem;
import com.mygitgor.restaurant.domain.Restaurant;
import com.mygitgor.restaurant.repository.IngredientCategoryRepository;
import com.mygitgor.restaurant.repository.IngredientItemRepository;
import com.mygitgor.restaurant.service.IngredientService;
import com.mygitgor.restaurant.service.RestaurantService;
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
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);

        IngredientCategory category = new IngredientCategory();
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
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> ingredientCategory = ingredientCategoryRepository.findById(id);

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
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
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

    /**
     * метод предназначен для поиска всех ингредиентов, связанных с определенным рестораном.
     * @param restaurantId идентификатор ресторана
     * @return возврошает список ингредиент итем указанного ресторана
     * @throws Exception бросает исключения Exception
     */
    @Override
    public List<IngredientItem> findRestaurantIngredients(Long restaurantId) throws Exception {
        return ingredientItemRepository.findByRestaurantId(restaurantId);
    }

    /**
     *  метод предназначен для обновления состояния запасов ингредиента.
     * @param id идентификатор ингредиент итем
     * @return возврошает обновленый ингредиент итем
     * @throws Exception бросает исключения Exception
     */
    @Override
    public IngredientItem updateStock(Long id) throws Exception {
       Optional<IngredientItem> ingredientItem = ingredientItemRepository.findById(id);

       if(ingredientItem.isEmpty()){
           throw new Exception("ingredient not found");
       }

       IngredientItem item = ingredientItem.get();
       item.setInStoke(!item.isInStoke());
       return ingredientItemRepository.save(item);
    }
}
