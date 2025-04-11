package com.mygitgor.restaurant.application.impl;

import com.mygitgor.restaurant.model.domain.Category;
import com.mygitgor.restaurant.model.domain.Restaurant;
import com.mygitgor.restaurant.model.repository.CategoryRepository;
import com.mygitgor.restaurant.application.service.CategoryService;
import com.mygitgor.restaurant.application.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * CategoryEntity Service: этот сервиз относятся к категориям еды
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
        category.setRestaurantId(restaurant.getId());

        return categoryRepository.save(category);
    }

    /**
     *  метод предназначен для поиска категорий, связанных с определенным рестораном по его идентификатору.
     * @param restaurantId идентификатор пользователя
     * @return возврошает список котегори
     * @throws Exception бросает исключения Exception
     */
    @Override
    public List<Category> findCategoryByRestaurantId(Long restaurantId) throws Exception {

        restaurantService.findRestaurantById(restaurantId);
        return categoryRepository.findByRestaurantId(restaurantId);
    }

    /**
     *  метод предназначен для поиска категории по ее идентификатору.
     * @param id идентификатор котегори
     * @return возврошает котегори
     * @throws Exception бросает исключения Exception
     */
    @Override
    public Category findCategoryById(Long id) throws Exception {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new Exception("Category not found"));
    }
}
