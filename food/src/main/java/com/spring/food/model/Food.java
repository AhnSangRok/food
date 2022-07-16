package com.spring.food.model;

import com.spring.food.dto.FoodRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Getter
@Service
@NoArgsConstructor
@Entity
public class Food{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long restaurantId;

    @Column(nullable = false)
    private int price;

    public Food (FoodRequestDto requestDto, Long RestaurantId){
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.restaurantId = RestaurantId;
    }
}
