package com.sparta.week2_homework.sevice;

import com.sparta.week2_homework.domain.Person;
import com.sparta.week2_homework.domain.PersonRepository;
import com.sparta.week2_homework.domain.PersonRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CourseService {
    private final PersonRepository personRepository;

    @Transactional
    public Long update(Long id, PersonRequestDto requestDto){
        Person person1 = personRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 없습니다.")
        );
        person1.update(requestDto);
        return person1.getId();
    }
}
