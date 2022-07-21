package com.spring.food.repository;

import com.spring.food.model.FoodOrder;
import com.spring.food.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {
    List<FoodOrder> findFoodOrderByOrders(Orders orders);
}
