package com.spring.food.dto;

import com.spring.food.model.FoodOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderRequestDto {
    private Long restaurantId;
    private List<FoodOrder> foods;
}
