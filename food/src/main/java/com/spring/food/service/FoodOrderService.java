package com.spring.food.service;

import com.spring.food.dto.FoodOrderDto;
import com.spring.food.dto.OrderDto;
import com.spring.food.dto.OrderRequestDto;
import com.spring.food.model.*;
import com.spring.food.repository.FoodOrderRepository;
import com.spring.food.repository.FoodRepository;
import com.spring.food.repository.OrdersRepository;
import com.spring.food.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class FoodOrderService {

    private final OrdersRepository ordersRepository;
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodOrderRepository foodOrderRepository;

    public List<OrderDto> getOrder(){
        List<OrderDto> orderDtos = new ArrayList<>();
        List<Orders> ordersList = ordersRepository.findAll();

        for (Orders orders : ordersList){
            List<FoodOrderDto> foodOrderDtoList = new ArrayList<>();
            List<FoodOrder> foodOrderList = foodOrderRepository.findFoodOrderByOrders(orders);
            for (FoodOrder foodOrder : foodOrderList){
                FoodOrderDto foodOrderDto = new FoodOrderDto(foodOrder);
                foodOrderDtoList.add(foodOrderDto);
            }
            int deliveryFee = restaurantRepository.findByName(orders.getRestaurantName()).getDeliveryFee();
            OrderDto orderDto = new OrderDto(orders, deliveryFee, foodOrderDtoList);
            orderDtos.add(orderDto);
        }

        return orderDtos;
    }

    public OrderDto createOrder(OrderRequestDto requestDto) throws Exception{
        Restaurant restaurant = restaurantRepository.findOneById(requestDto.getRestaurantId());
        List<FoodOrderDto> foodOrderDto = new ArrayList<>();
        List<FoodOrder> foodOrders = requestDto.getFoods();
        List<FoodOrder> foodOrderList = new ArrayList<>();

        int totalPrice = 0;
        for (FoodOrder foodOrder : foodOrders){
            if (foodOrder.getQuantity() < 1 || foodOrder.getQuantity() > 100){
                throw new Exception("주문 수량은 1부터 100까지만 가능합니다");
            }

            Food food  = foodRepository.getById(foodOrder.getId());

            FoodOrder foodOrder1 = FoodOrder.builder()
                    .quantity(foodOrder.getQuantity())
                    .name(food.getName())
                    .price(food.getPrice() * foodOrder.getQuantity())
                    .food(food)
                    .build();
            foodOrderRepository.save(foodOrder1);
            FoodOrderDto foodOrderDto1 = new FoodOrderDto(foodOrder1);
            foodOrderDto.add(foodOrderDto1);
            totalPrice += food.getPrice() * foodOrder.getQuantity();
            foodOrderList.add(foodOrder1);
        }

        if (restaurant.getMinOrderPrice() > totalPrice){
            throw new Exception("최소주문 가격 부족");
        }
        int deliveryFee = restaurant.getDeliveryFee();
        totalPrice += deliveryFee;

        Orders orders = Orders.builder()
                .restaurantName(restaurant.getName())
                .totalPrice(totalPrice)
                .foods(foodOrderList)
                .build();
        ordersRepository.save(orders);

        OrderDto orderDto = new OrderDto(orders, deliveryFee, foodOrderDto);

        return orderDto;
    }
}
