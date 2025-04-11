package com.mygitgor.restaurant.application.impl;

import com.mygitgor.restaurant.model.domain.IngredientCategory;
import com.mygitgor.restaurant.model.domain.IngredientItem;
import com.mygitgor.restaurant.model.domain.Restaurant;
import com.mygitgor.restaurant.model.repository.IngredientCategoryRepository;
import com.mygitgor.restaurant.model.repository.IngredientItemRepository;
import com.mygitgor.restaurant.application.service.IngredientService;
import com.mygitgor.restaurant.application.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        restaurantService.findRestaurantById(restaurantId);

        IngredientCategory category = new IngredientCategory();
        category.setRestaurantId(restaurantId);
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
       return ingredientCategoryRepository.findById(id)
               .orElseThrow(() -> new Exception("Ingredient category not found"));
    }

    /**
     * метод предназначен для поиска категорий ингредиентов по идентификатору ресторана.
     * @param restaurantId идентификатор ресторана
     * @return возврошает список ингредиент категория
     * @throws Exception бросает исключения Exception
     */
    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long restaurantId) throws Exception {
        restaurantService.findRestaurantById(restaurantId);
        return ingredientCategoryRepository.findByRestaurantId(restaurantId);
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
        item.setRestaurantId(restaurantId);
        item.setCategoryId(categoryId);

        return ingredientItemRepository.save(item);
    }

    /**
     * метод предназначен для поиска всех ингредиентов, связанных с определенным рестораном.
     * @param restaurantId идентификатор ресторана
     * @return возврошает список ингредиент итем указанного ресторана
     * @throws Exception бросает исключения Exception
     */
    @Override
    public List<IngredientItem> findRestaurantIngredients(Long restaurantId) throws Exception {
        restaurantService.findRestaurantById(restaurantId);
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
        IngredientItem item = ingredientItemRepository.findById(id)
                .orElseThrow(() -> new Exception("Ingredient not found"));

        item.setInStock(!item.isInStock());
       return ingredientItemRepository.save(item);
    }
}
