package com.sparta.springcore.model;

import com.sparta.springcore.dto.NoticeRequestDto;
import com.sparta.springcore.dto.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Notice extends Timestamped {
    @Id // ID 값, Primary Key로 사용하겠다는 뜻입니다.
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동 증가 명령입니다.
    private Long id;

    @Column(nullable = false) // 컬럼 값이고 반드시 값이 존재해야 함을 나타냅니다.
    private String title;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private Long userId;

    public Notice(NoticeRequestDto requestDto, Long userId){
        this.userId = userId;
        this.title = requestDto.getTitle();
        this.name = requestDto.getName();
        this.text = requestDto.getText();
    }
}
