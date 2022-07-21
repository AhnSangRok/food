package com.spring.food.repository;

import com.spring.food.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findAll() ;
//    Restaurant findById(Long id) ;
    Restaurant findByName(String name);

    Restaurant findOneById(Long restaurantId);
}
