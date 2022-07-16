package com.spring.food.service;

import com.spring.food.dto.RestaurantRequestDto;
import com.spring.food.model.Restaurant;
import com.spring.food.model.RestaurantRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    public List<Restaurant> getRestaurant() {
        return this.restaurantRepository.findAll();
    }

    public String createResturant(RestaurantRequestDto requestDto) {
        String error = "";
        int minOrderPrice = requestDto.getMinOrderPrice();
        int deliveryFee = requestDto.getDeliveryFee();

        if (minOrderPrice%100 > 0){
            return "100원 단위부터 입력 가능합니다.";
        } else if (deliveryFee%500 > 0) {
            return "500원 단위부터 입력 가능합니다.";
        }

        Restaurant restaurant = new Restaurant(requestDto);
        this.restaurantRepository.save(restaurant);

        return error;
    }
}
