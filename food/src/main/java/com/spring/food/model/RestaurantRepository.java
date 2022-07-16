package com.spring.food.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findAll() ;
    List<Restaurant> findAllById(Long id) ;

}
