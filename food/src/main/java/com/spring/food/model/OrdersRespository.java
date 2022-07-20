package com.spring.food.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRespository extends JpaRepository<Orders, Long> {
    List<Orders> findAll();
}
