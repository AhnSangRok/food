package com.sparta.springcore.controller;

import com.sparta.springcore.dto.NoticeRequestDto;
import com.sparta.springcore.model.Notice;
import com.sparta.springcore.repository.NoticeRepository;
import com.sparta.springcore.security.UserDetailsImpl;
import com.sparta.springcore.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeRepository noticeRepository;
    private final NoticeService noticeService;

    //전체 글 조회
    @GetMapping("/homepage/notice")
    public List<Notice> getNotice(){
        return noticeRepository.findAllByOrderByModifiedAtDesc();
    }

    //글 게시
    @PostMapping("/homepage/notice")
    public Notice createNotice(@RequestBody NoticeRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Long userId = userDetails.getUser().getId();
        Notice notice = noticeService.createNotice(requestDto, userId);
        return notice;
    }
    //글 수정
    @PutMapping("/homepage/notice/{id}")
    public Long updateNotice(@PathVariable Long id, @RequestBody NoticeRequestDto requestDto){
        Notice notice = noticeService.updateNotice(id, requestDto);
        return notice.getId();
    }
    @DeleteMapping("/homepage/notice/{id}")
    public Optional<Notice> getNoticeOne(@PathVariable Long id){
        return noticeRepository.findById(id);
    }
}
