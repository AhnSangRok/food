package com.spring.food.service;

import com.spring.food.dto.FoodRequestDto;
import com.spring.food.model.Food;
import com.spring.food.model.FoodRepository;
import com.spring.food.model.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    public List<Food> getFood(Long restaurantId) {
        return this.foodRepository.findAllByRestaurantId(restaurantId);
    }
    public String createFood(FoodRequestDto requestDto, Long RestaurantId) {
        String error = "";
        String name = requestDto.getName();
        int price = requestDto.getPrice();
//            return "음식점이 존재하지 않습니다.";
        List<Food> foods = foodRepository.findAllByRestaurantId(RestaurantId);
        Optional<Food> foodName = foodRepository.findByName(name);

        if (foodName.isPresent()){
            System.out.println(name);
            return "중복되는 음식이 있습니다.";
        } else if (price%100 > 0) {
            return "100원 단위부터 입력 가능합니다.";
        }

        Food food = new Food(requestDto, RestaurantId);
        this.foodRepository.save(food);

        return error;
    }
}
