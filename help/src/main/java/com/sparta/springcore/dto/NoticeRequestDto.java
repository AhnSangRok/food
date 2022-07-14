package com.sparta.springcore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NoticeRequestDto {
    private String title;
    private String name;
    private String text;
}