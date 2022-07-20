package com.spring.food.service;

import com.spring.food.dto.FoodRequestDto;
import com.spring.food.model.Food;
import com.spring.food.model.FoodRepository;
import com.spring.food.model.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    public List<Food> getFood(Long restaurantId) {
        return this.foodRepository.findAllByRestaurantId(restaurantId);
    }

    public Food createFood(FoodRequestDto requestDto, Long RestaurantId) throws Exception {
        String name = requestDto.getName();
        int price = requestDto.getPrice();

//            return "음식점이 존재하지 않습니다.";
        List<Food> foods = foodRepository.findAllByRestaurantId(RestaurantId);

        //중복되는 음식 확인
        if (foods!=null){
            for (Food value : foods) {
                if (Objects.equals(value.getName(), name)) {
                    throw new Exception("중복되는 음식이 있습니다.");

                }
            }
        }
        //음식 가격 확인
        if (price % 100 > 0) {
            throw new Exception("100원 단위부터 입력 가능합니다.");
        } else if (price < 100 || price > 1000000) {
            throw new Exception("100원에서 1000000까지만 가능합니다.");
        }

        Food food = new Food(requestDto,RestaurantId);

        return foodRepository.save(food);
    }
}
