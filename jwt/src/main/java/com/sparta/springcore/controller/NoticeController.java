package com.sparta.springcore.controller;

import com.sparta.springcore.dto.NoticeRequestDto;
import com.sparta.springcore.model.Notice;
import com.sparta.springcore.model.NoticeRepository;
import com.sparta.springcore.security.UserDetailsImpl;
import com.sparta.springcore.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class NoticeController {
    private final NoticeRepository noticeRepository;
    private final NoticeService noticeService;

    @GetMapping({"/homepage/notice"})
    public List<Notice> getNotice() {
        return this.noticeService.getNotice();
    }

    @PostMapping({"/homepage/notice"})
    public Notice createNotice(@RequestBody NoticeRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        System.out.println(userId);
        System.out.println(requestDto.getText());
        Notice notice = this.noticeService.createNotice(requestDto, userId);
        return notice;
    }

    @PutMapping({"/homepage/notice/{id}"})
    public Long updateNotice(@PathVariable Long id, @RequestBody NoticeRequestDto requestDto) {
        Notice notice = this.noticeService.updateNotice(id, requestDto);
        return notice.getId();
    }

    @DeleteMapping({"/homepage/notice/{id}"})
    public Optional<Notice> getNoticeOne(@PathVariable Long id) {
        return noticeRepository.findById(id);
    }

}
