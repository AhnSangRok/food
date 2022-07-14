package com.notice.noticeboard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class NoticeRequestDto {
    private String title;
    private String name;
    private String password;
    private String text;

}
