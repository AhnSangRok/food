package com.spring.food.controller;

import com.spring.food.dto.RestaurantRequestDto;
import com.spring.food.model.Restaurant;
import com.spring.food.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurant(){
        return restaurantService.getRestaurant();
    }

    @PostMapping("restaurants/register")
    public String createRestaurant(@RequestBody RestaurantRequestDto requestDto) {
        String createM = restaurantService.createResturant(requestDto);
        if (createM.equals("")) {
            System.out.println(createM);
            return "입력 성공";
        } else {
            System.out.println(createM);
            return createM;
        }
    }

}
