package com.sparta.week2_homework.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Person  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long age;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String job;

    public Person(PersonRequestDto requestDto){
        this.name = requestDto.getName();
        this.age = requestDto.getAge();
        this.address = requestDto.getAddress();
        this.job = requestDto.getJob();
    }
    public Person(String name,Long age,String address,String job){
        this.name =name;
        this.age = age;
        this.address = address;
        this.job = job;
    }
    public void update(PersonRequestDto requestDto){
        this.name = requestDto.getName();
        this.age = requestDto.getAge();
        this.address = requestDto.getAddress();
        this.job = requestDto.getJob();
    }
}
