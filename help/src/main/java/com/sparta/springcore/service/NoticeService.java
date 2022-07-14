package com.sparta.springcore.service;

import com.sparta.springcore.dto.NoticeRequestDto;
import com.sparta.springcore.model.Notice;
import com.sparta.springcore.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    public Notice createNotice(NoticeRequestDto requestDto, Long userId) {
        Notice notice = new Notice(requestDto, userId);
        noticeRepository.save(notice);
        return notice;
    }

    public Notice updateNotice(Long id, NoticeRequestDto requestDto) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));
        String text = requestDto.getText();
        notice.setText(text);
        noticeRepository.save(notice);

        return notice;
    }
}
