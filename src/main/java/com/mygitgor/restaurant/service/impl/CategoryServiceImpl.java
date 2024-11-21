package com.mygitgor.restaurant.service.impl;

import com.mygitgor.restaurant.domain.Category;
import com.mygitgor.restaurant.domain.Restaurant;
import com.mygitgor.restaurant.repository.CategoryRepository;
import com.mygitgor.restaurant.service.CategoryService;
import com.mygitgor.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Category Service: этот сервиз относятся к категориям еды
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final RestaurantService restaurantService;
    private final CategoryRepository categoryRepository;

    /**
     *  метод предназначен для создания новой категории в ресторане.
     * @param name нозвания котегори
     * @param userId идентификатор пользователя
     * @return возврошает созданный категори
     * @throws Exception бросает исключения Exception
     */
    @Override
    public Category createCategory(String name, Long userId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantByUserId(userId);

        Category category = new Category();
        category.setName(name);
        category.setRestaurant(restaurant);

        return categoryRepository.save(category);
    }

    /**
     *  метод предназначен для поиска категорий, связанных с определенным рестораном по его идентификатору.
     * @param id идентификатор пользователя
     * @return возврошает список котегори
     * @throws Exception бросает исключения Exception
     */
    @Override
    public List<Category> findCategoryByRestaurantId(Long id) throws Exception {

        Restaurant restaurant = restaurantService.findRestaurantById(id);
        return categoryRepository.findByRestaurantId(restaurant.getId());
    }

    /**
     *  метод предназначен для поиска категории по ее идентификатору.
     * @param id идентификатор котегори
     * @return возврошает котегори
     * @throws Exception бросает исключения Exception
     */
    @Override
    public Category findCategoryById(Long id) throws Exception {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            throw new Exception("category not found");
        }
        return category.get();
    }
}
