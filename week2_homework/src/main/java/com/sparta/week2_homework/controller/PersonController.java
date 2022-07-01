package com.sparta.week2_homework.controller;

import com.sparta.week2_homework.domain.Person;
import com.sparta.week2_homework.domain.PersonRepository;
import com.sparta.week2_homework.domain.PersonRequestDto;
import com.sparta.week2_homework.domain.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository personRepository;
    private final PersonService personService;

    @PostMapping("/api/people")
    public Person createPerson(@RequestBody PersonRequestDto requestDto){
        Person person = new Person(requestDto);
        return personRepository.save(person);
    }

    @GetMapping("/api/people")
    public List<Person> getPerson(){return personRepository.findAll();}

    @PutMapping("/api/people/{id}")
    public Long updatePerson(@PathVariable Long id, @RequestBody PersonRequestDto requestDto){
        return personService.update(id, requestDto);
    }

    @DeleteMapping("/api/people/{id}")
    public Long deletePerson(@PathVariable Long id){
        personRepository.deleteById(id);
        return id;
    }
}
