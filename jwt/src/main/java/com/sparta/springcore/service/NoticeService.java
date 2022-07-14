package com.sparta.springcore.service;

import com.sparta.springcore.dto.NoticeRequestDto;
import com.sparta.springcore.model.Notice;
import com.sparta.springcore.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service // 스프링에게 이 클래스는 서비스임을 명시
public class NoticeService {

    // final: 서비스에게 꼭 필요한 녀석임을 명시
    private final NoticeRepository noticeRepository;

    public Notice createNotice(NoticeRequestDto requestDto, Long userId ) {
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Notice notice = new Notice(requestDto, userId);

        noticeRepository.save(notice);

        return notice;
    }


    @Transactional // SQL 쿼리가 일어나야 함을 스프링에게 알려줌
    public Notice updateNotice(Long id, NoticeRequestDto requestDto) {
        Notice notice = noticeRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        notice.update(requestDto);
        return notice;
    }
    @Transactional // SQL 쿼리가 일어나야 함을 스프링에게 알려줌
    public Notice deleteNotice(Long id) {
        Notice notice = noticeRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        noticeRepository.deleteById(id);
        return notice;
    }
    //전체 보기
    public List<Notice> getNotice() {
        return noticeRepository.findAllByOrderByModifiedAtDesc();
    }
    // 개인이 올린 글보기
    public List<Notice> getMyNotice(Long userId) {
        return noticeRepository.findByIdOrderByModifiedAtDesc(userId);
    }
}