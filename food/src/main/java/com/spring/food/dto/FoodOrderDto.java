package com.spring.food.dto;

import com.spring.food.model.FoodOrder;
import lombok.Getter;

@Getter
public class FoodOrderDto {
    private String name;
    private int quantity;
    private int price;

    public FoodOrderDto(FoodOrder foodOrder) {
        this.name = foodOrder.getName();
        this.quantity = foodOrder.getQuantity();
        this.price = foodOrder.getPrice();
    }
}
