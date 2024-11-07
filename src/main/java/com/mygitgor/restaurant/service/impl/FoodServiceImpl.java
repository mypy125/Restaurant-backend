package com.mygitgor.restaurant.service.impl;

import com.mygitgor.restaurant.domain.Category;
import com.mygitgor.restaurant.domain.Food;
import com.mygitgor.restaurant.domain.Restaurant;
import com.mygitgor.restaurant.repository.FoodRepository;
import com.mygitgor.restaurant.request.CreateFoodRequest;
import com.mygitgor.restaurant.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Food Service: этот сервиз относятся к продуктам.
 */
@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {
    private final FoodRepository foodRepository;

    /**
     *  метод предназначен для создания нового блюда в ресторане.
     * @param request принемает запроос с необходимых параметров для создания еды
     * @param category принемает конкретный категория
     * @param restaurant принемает конкретный ресторан
     * @return возврошает блюду
     */
    @Override
    public Food createFood(CreateFoodRequest request, Category category, Restaurant restaurant) {
        Food food = new Food();
        food.setFoodCategory(category);
        food.setRestaurant(restaurant);
        food.setDescription(request.getDescription());
        food.setImages(request.getImages());
        food.setPrice(request.getPrice());
        food.setIngredients(request.getIngredients());
        food.setSeasonal(request.isSeasonal());
        food.setCreateondate(new Date());
        food.setVegetarian(request.isVegetarian());

        Food FoodSave = foodRepository.save(food);
        restaurant.getFoods().add(FoodSave);
        return FoodSave;
    }

    /**
     * метод предназначен для удаления блюда из базы данных.
     * @param foodId идентификатор блюд
     * @throws Exception бросает исключения Exception
     */
    @Override
    public void deleteFood(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.save(food);
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
        if(foodCategory != null && !foodCategory.equals("")){
            foods = filterByCategory(foods, foodCategory);
        }
        return null;
    }

    /**
     * метод предназначен для получения списка блюд из определенного котегори.
     * @param foods список блюд
     * @param foodCategory нозвания категори
     * @return список блюд из котегори
     */
    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
        return foods.stream().filter(food -> {
            if(food.getFoodCategory() != null){
                return food.getFoodCategory().equals(foodCategory);
            }
            return false;
        }).collect(Collectors.toList());
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
        Optional<Food> food = foodRepository.findById(id);
        if(food.isEmpty()){
            throw  new Exception("Food not found");
        }
        return food.get();
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
