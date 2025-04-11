package com.mygitgor.restaurant.application.impl;

import com.mygitgor.restaurant.model.domain.Category;
import com.mygitgor.restaurant.model.domain.Food;
import com.mygitgor.restaurant.model.domain.IngredientItem;
import com.mygitgor.restaurant.model.domain.Restaurant;
import com.mygitgor.restaurant.model.repository.CategoryRepository;
import com.mygitgor.restaurant.model.repository.FoodRepository;
import com.mygitgor.restaurant.model.repository.IngredientItemRepository;
import com.mygitgor.restaurant.model.repository.RestaurantRepository;
import com.mygitgor.restaurant.api.controller.DTOs.request.CreateFoodRequest;
import com.mygitgor.restaurant.application.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * FoodEntity Service: этот сервиз относятся к продуктам.
 */
@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;
    private final CategoryRepository categoryRepository;
    private final IngredientItemRepository ingredientItemRepository;

    /**
     *  метод предназначен для создания нового блюда в ресторане.
     * @param request принемает запроос с необходимых параметров для создания еды
     * @return возврошает блюду
     */
    @Transactional
    @Override
    public Food createFood(CreateFoodRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));

        List<IngredientItem> ingredients = ingredientItemRepository.findAllById(request.getIngredientIds());
        if (ingredients.size() != request.getIngredientIds().size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some ingredients not found");
        }
        List<Long> ingredientIds = ingredients.stream()
                .map(IngredientItem::getId)
                .toList();

        Food createFood = new Food();
        createFood.setName(request.getName());
        createFood.setDescription(request.getDescription());
        createFood.setPrice(request.getPrice());
        createFood.setCategoryId(category.getId());
        createFood.setRestaurantId(restaurant.getId());
        createFood.setIngredientIds(ingredientIds);
        createFood.setSeasonal(request.isSeasonal());
        createFood.setVegetarian(request.isVegetarian());
        createFood.setCreatedOnDate(new Date());
        createFood.setImages(request.getImages());

        return foodRepository.save(createFood);
    }


    /**
     * метод предназначен для удаления блюда из базы данных.
     * @param foodId идентификатор блюд
     * @throws Exception бросает исключения Exception
     */
    @Override
    public void deleteFood(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        foodRepository.delete(food);
    }

    /**
     * метод предназначен для получения списка блюд из определенного ресторана с опциональными фильтрами.
     * @param restaurantId идентификатор ресторана
     * @param isVegetarian блюда является вегитарянский
     * @param isNonveg блюда является не вегетарианский
     * @param isSeasonal блюда является сезонным
     * @param foodCategory категория блюд
     * @return возврошает список блюд
     */
    @Override
    public List<Food> getRestaurantFood(Long restaurantId,
                                              boolean isVegetarian,
                                              boolean isNonveg,
                                              boolean isSeasonal,
                                              String foodCategory) {
        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);
        if(isVegetarian){
            foods = filterByVegetarian(foods, isVegetarian);
        }
        if(isNonveg){
            foods = filterByNonveg(foods, isNonveg);
        }
        if(isSeasonal){
            foods = filterBySeasonal(foods, isSeasonal);
        }
        if(foodCategory != null && !foodCategory.trim().isEmpty()){
            foods = filterByCategory(foods, foodCategory);
        }
        return foods;
    }

    /**
     * метод предназначен для получения списка блюд из определенного котегори.
     * @param foods список блюд
     * @param categoryName нозвания категори
     * @return список блюд из котегори
     */
    private List<Food> filterByCategory(List<Food> foods, String categoryName) {
        if (categoryName == null || categoryName.isBlank()) {
            return new ArrayList<>();
        }

        Optional<Category> category = categoryRepository.findByName(categoryName);
        if (category.isEmpty()) {
            return new ArrayList<>();
        }

        Long categoryId = category.get().getId();
        return foods.stream()
                .filter(food -> categoryId.equals(food.getCategoryId()))
                .collect(Collectors.toList());
    }

    /**
     * метод предназначен для получения списка блюд из определенного сезонным.
     * @param foods список блюд
     * @param isSeasonal сезон
     * @return возврошает список сезоннх блюд
     */
    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream().filter(food -> food.isSeasonal() == isSeasonal).collect(Collectors.toList());

    }

    /**
     * метод предназначен для получения списка блюд не вегетарианский.
     * @param foods список блюд
     * @param isNonveg не вегетарианский
     * @return  возврошает список не вегетарианский блюд
     */
    private List<Food> filterByNonveg(List<Food> foods, boolean isNonveg) {
        return foods.stream().filter(food -> food.isVegetarian() == false).collect(Collectors.toList());

    }

    /**
     * метод предназначен для получения списка блюд вегетарианский.
     * @param foods список блюд
     * @param isVegetarian вегетарианский
     * @return возврошает список вегетарианский блюд
     */
    private List<Food> filterByVegetarian(List<Food> foods, boolean isVegetarian) {
        return foods.stream().filter(food -> food.isVegetarian() == isVegetarian).collect(Collectors.toList());
    }

    /**
     * метод предназначен для поиска блюд по ключевому слову.
     * Он вызывает метод searchFood репозитория foodRepository,
     * который выполняет поиск блюд по заданному ключевому слову в их названии,
     * описании или других полях.
     * @param keyword ключевому слова
     * @return возврошает список блюд
     */
    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    /**
     *  метод используется для поиска блюда по его идентификатору.
     * @param id идентификатор блюд
     * @return возврошает блюдо
     * @throws Exception бросает исключения Exception
     */
    @Override
    public Food findFoodById(Long id) throws Exception {
        return foodRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "FoodEntity not found with id: " + id));
    }


    /**
     * метод предназначен для обновления статуса доступности блюда.
     * @param foodId идентификатор блюд
     * @return возврошает блюду обновленным доступом
     * @throws Exception бросает исключения Exception
     */
    @Override
    public Food updateAvailabilityStatus(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);
    }
}
