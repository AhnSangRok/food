package com.sparta.springcore.service;

import com.sparta.springcore.dto.NoticeRequestDto;
import com.sparta.springcore.model.Notice;
import com.sparta.springcore.model.NoticeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public Notice createNotice(NoticeRequestDto requestDto, Long userId) {
        Notice notice = new Notice(requestDto, userId);
        this.noticeRepository.save(notice);
        return notice;
    }

    public Notice updateNotice(Long id, NoticeRequestDto requestDto) {
        Notice notice = (Notice)this.noticeRepository.findById(id).orElseThrow(() -> {
            return new NullPointerException("해당 아이디가 존재하지 않습니다.");
        });
        String text = requestDto.getText();
        notice.setText(text);
        this.noticeRepository.save(notice);
        return notice;
    }

    public List<Notice> getNotice() {
        return this.noticeRepository.findAllByOrderByModifiedAtDesc();
    }

    public NoticeService(final NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }
}
