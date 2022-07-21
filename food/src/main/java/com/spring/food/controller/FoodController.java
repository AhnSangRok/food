package com.spring.food.controller;

import com.spring.food.dto.FoodRequestDto;
import com.spring.food.model.Food;
import com.spring.food.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FoodController{

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }


    @GetMapping("/restaurant/{restaurantId}/food")
    public List<Food> getFoot(@PathVariable Long restaurantId){
        return foodService.getFood(restaurantId);
    }

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void createFood(@RequestBody List<FoodRequestDto> requestDto, @PathVariable Long restaurantId) throws Exception {
        foodService.createFood(requestDto, restaurantId);

    }
}
