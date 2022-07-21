package com.spring.food.service;

import com.spring.food.dto.RestaurantRequestDto;
import com.spring.food.model.Restaurant;
import com.spring.food.repository.RestaurantRepository;
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

    public Restaurant register(RestaurantRequestDto requestDto) throws Exception {

        int minOrderPrice = requestDto.getMinOrderPrice();
        int deliveryFee = requestDto.getDeliveryFee();

        //최소 주문가능 가격
        if (minOrderPrice % 100 > 0) {
            throw new Exception("100원 단위부터 입력 가능합니다.");
        } else if (minOrderPrice < 1000 || minOrderPrice > 100000) {
            throw new Exception("1000원에서 100000원까지 입력가능합니다.");
        }
        //배달료
        if (deliveryFee < 0 || deliveryFee > 10000) {
            throw new Exception("0원에서 10000원까지 입력가능합니다.");
        } else if (deliveryFee % 500 > 0) {
            throw new Exception("500원 단위부터 입력 가능합니다.");
        }

        Restaurant restaurant = Restaurant.builder()
                .name(requestDto.getName())
                .minOrderPrice(minOrderPrice)
                .deliveryFee(deliveryFee)
                .build();

        restaurantRepository.save(restaurant);

        return restaurant;
    }

//    public Restaurant register(RestaurantRequestDto requestDto) {
//    }
}
