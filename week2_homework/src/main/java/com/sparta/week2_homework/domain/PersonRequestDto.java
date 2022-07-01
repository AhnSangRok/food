package com.sparta.week2_homework.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PersonRequestDto {
    private final String name;
    private final Long age;
    private final String address;
    private final String job;
}
