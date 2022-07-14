package com.sparta.springcore.controller;

import com.sparta.springcore.dto.NoticeRequestDto;
import com.sparta.springcore.model.Notice;
import com.sparta.springcore.model.Product;
import com.sparta.springcore.repository.NoticeRepository;
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
    private final NoticeService noticeService;

    @GetMapping("/api/notice")
    public List<Notice> getNotice(){
        return noticeService.getNotice();
    }

    @PostMapping("/api/notice")
    public Notice createNotice(@RequestBody NoticeRequestDto requestDto,
                               @AuthenticationPrincipal UserDetailsImpl userDetails){

        Long userId = userDetails.getUser().getId();

        Notice notice = noticeService.createNotice(requestDto, userId);

        return notice;
    }
    @PutMapping("/api/notice/{id}")
    public Long updateNotice(@PathVariable Long id, @RequestBody NoticeRequestDto noticeRequestDto) {
        Notice notice = noticeService.updateNotice(id, noticeRequestDto);

        // 응답 보내기 (업데이트된 상품 id)
        return notice.getId();
    }
    @DeleteMapping("/api/notice/{id}")
    public Long deleteNotice(@PathVariable Long id) {
        Notice notice = noticeService.deleteNotice(id);

        return notice.getId();
    }

    @GetMapping("/api/mynotice")
    public List<Notice> getNotice(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        return noticeService.getMyNotice(userId);
    }
}
