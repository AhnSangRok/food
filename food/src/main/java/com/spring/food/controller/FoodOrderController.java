package com.spring.food.controller;

import com.spring.food.dto.OrderDto;
import com.spring.food.dto.OrderRequestDto;
import com.spring.food.service.FoodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
public class FoodOrderController {
    private final FoodOrderService foodOrderService;

    @Autowired
    public FoodOrderController(FoodOrderService foodOrderService){this.foodOrderService = foodOrderService;}

    @GetMapping("/order")
    public List<OrderDto> getOrder(){ return foodOrderService.getOrder(); }

    @PostMapping("/order/request")
    public OrderDto createOrder(@RequestBody OrderRequestDto requestDto) throws Exception {
        return  foodOrderService.createOrder(requestDto);
    }
}
