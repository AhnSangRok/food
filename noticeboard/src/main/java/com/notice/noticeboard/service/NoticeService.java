package com.notice.noticeboard.service;

import com.notice.noticeboard.model.Notice;
import com.notice.noticeboard.repository.NoticeRepository;
import com.notice.noticeboard.dto.NoticeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service // 스프링에게 이 클래스는 서비스임을 명시
public class NoticeService {

    // final: 서비스에게 꼭 필요한 녀석임을 명시
    private final NoticeRepository noticeRepository;

    @Transactional // SQL 쿼리가 일어나야 함을 스프링에게 알려줌
    public Long update(Long id, NoticeRequestDto requestDto) {
        Notice notice = noticeRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        notice.update(requestDto);
        return notice.getId();
    }
}