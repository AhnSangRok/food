package com.sparta.week2_homework.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Transactional
    public Long update(Long id, PersonRequestDto requestDto){
        Person person1 =personRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 없어유")
        );
        person1.update(requestDto);
        return  person1.getId();
    }
}
