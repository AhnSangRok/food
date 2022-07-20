package com.spring.food.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FoodOrderDto {
    private String name;
    private int quantity;
    private int price;
}
