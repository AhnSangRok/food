package com.spring.food.controller;

import com.spring.food.dto.RestaurantRequestDto;
import com.spring.food.model.Restaurant;
import com.spring.food.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/restaurant")
    public List<Restaurant> getRestaurant() {
        return restaurantService.getRestaurant();
    }

    @PostMapping("/restaurant/register") // 음식점 등록 , Response값 없음
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody RestaurantRequestDto requestDto) throws Exception {
        Restaurant restaurant = restaurantService.register(requestDto);
        return ResponseEntity.ok().body(restaurant);
    }

}
