package com.notice.noticeboard.domain;

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
