package com.notice.noticeboard.controller;

import com.notice.noticeboard.domain.Notice;
import com.notice.noticeboard.domain.NoticeRepository;
import com.notice.noticeboard.domain.NoticeRequestDto;
import com.notice.noticeboard.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class NoticeController {
    private final NoticeRepository noticeRepository;
    private final NoticeService noticeService;

    @GetMapping("/api/notice")
    public List<Notice> getNotice(){
        return noticeRepository.findAllByOrderByModifiedAtDesc();
    }

    @PostMapping("/api/notice")
    public Notice createNotice(@RequestBody NoticeRequestDto requestDto){
        Notice notice = new Notice(requestDto);
        return noticeRepository.save(notice);
    }
    @PutMapping("/api/notice/{id}")
    public Long updateNotice(@PathVariable Long id, @RequestBody NoticeRequestDto requestDto) {
        noticeService.update(id, requestDto);
        return id;
    }
    @DeleteMapping("/api/notice/{id}")
    public Long deleteNotice(@PathVariable Long id) {
        noticeRepository.deleteById(id);
        return id;
    }
    @GetMapping("/api/notice/{id}")
    public Optional<Notice> getOneNotice(@PathVariable Long id){
        return noticeRepository.findById(id);
    }

}
