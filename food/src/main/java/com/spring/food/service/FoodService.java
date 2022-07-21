package com.spring.food.service;

import com.spring.food.dto.FoodRequestDto;
import com.spring.food.model.Food;
import com.spring.food.model.Restaurant;
import com.spring.food.repository.FoodRepository;
import com.spring.food.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    public List<Food> getFood(Long restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        return this.foodRepository.findFoodsByRestaurant(restaurant);
    }

    public void createFood(List<FoodRequestDto> requestDto, Long RestaurantId) throws Exception {

        Optional<Restaurant> foundRestaurant = restaurantRepository.findById(RestaurantId);
        Restaurant restaurant = foundRestaurant.get();
//            return "음식점이 존재하지 않습니다.";

        for(FoodRequestDto foods : requestDto){
            String foodN = foods.getName();
            int foodPrice = foods.getPrice();
            //중복되는 음식 확인
            Optional<Food> foodCheck = foodRepository.findFoodByRestaurantAndName(restaurant, foodN);
            if (foodCheck.isPresent())
                throw new Exception("중복되는 음식입니다.");
            //음식 가격 확인
            if (foodPrice % 100 > 0) {
                throw new Exception("100원 단위부터 입력 가능합니다.");
            } else if (foodPrice < 100 || foodPrice > 1000000) {
                throw new Exception("100원에서 1000000까지만 가능합니다.");
            }

            Food food = Food.builder()
                    .name(foodN)
                    .price(foodPrice)
                    .restaurant(restaurant)
                    .build();
            foodRepository.save(food);
        }
    }
}
