package com.spring.food.repository;

import com.spring.food.model.Food;
import com.spring.food.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findAllByRestaurantId(Long restaurantId) ;
    Optional<Food> findByName(String name);
    List<Food> findFoodsByRestaurant(Optional<Restaurant> restaurant);
    Optional<Food> findFoodByRestaurantAndName(Restaurant restaurant, String foodName);
}
