package com.mygitgor.restaurant.application.impl;

import com.mygitgor.restaurant.infrastructure.database.entity.CategoryEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.RestaurantEntity;
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
    public CategoryEntity createCategory(String name, Long userId) throws Exception {
        RestaurantEntity restaurant = restaurantService.findRestaurantByUserId(userId);

        CategoryEntity category = new CategoryEntity();
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
    public List<CategoryEntity> findCategoryByRestaurantId(Long id) throws Exception {

        RestaurantEntity restaurant = restaurantService.findRestaurantById(id);
        return categoryRepository.findByRestaurantId(restaurant.getId());
    }

    /**
     *  метод предназначен для поиска категории по ее идентификатору.
     * @param id идентификатор котегори
     * @return возврошает котегори
     * @throws Exception бросает исключения Exception
     */
    @Override
    public CategoryEntity findCategoryById(Long id) throws Exception {
        Optional<CategoryEntity> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            throw new Exception("category not found");
        }
        return category.get();
    }
}
