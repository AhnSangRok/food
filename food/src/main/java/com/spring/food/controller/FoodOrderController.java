package com.spring.food.controller;

import com.spring.food.dto.OrderDto;
import com.spring.food.model.Orders;
import com.spring.food.service.FoodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoodOrderController {
    private final FoodOrderService foodOrderService;

    @Autowired
    public FoodOrderController(FoodOrderService foodOrderService){this.foodOrderService = foodOrderService;}

    @PostMapping("/order/request")
    public RequestEntity<Orders> createOrder(@RequestBody OrderDto orderDto){
        return null;
    }
}
