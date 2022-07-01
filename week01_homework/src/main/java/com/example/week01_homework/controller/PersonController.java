package com.example.week01_homework.controller;

import com.example.week01_homework.person.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @GetMapping("/myinfo")
    public Person getCourses() {
        Person person = new Person();
        person.setName("안상록");
        person.setAge(25);
        person.setAddress("경상북도");
        person.setJob("자택경비원");
        return person;
    }

}
