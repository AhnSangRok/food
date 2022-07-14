package com.notice.noticeboard.model;

import com.notice.noticeboard.dto.NoticeRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본생성자를 대신 생성해줍니다.
@Entity // 테이블임을 나타냅니다.
public class User{

    @Id // ID 값, Primary Key로 사용하겠다는 뜻입니다.
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동 증가 명령입니다.
    private Long id;

    @Column(nullable = false) // 컬럼 값이고 반드시 값이 존재해야 함을 나타냅니다.
    private String nickname;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;

    }

}
