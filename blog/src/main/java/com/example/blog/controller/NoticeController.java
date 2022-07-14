package com.example.blog.controller;

import com.example.blog.dto.NoticeRequestDto;
import com.example.blog.model.Notice;
import com.example.blog.repository.NoticeRepository;
import com.example.blog.service.NoticeService;
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
    public Boolean updateNotice(@PathVariable Long id, @RequestBody NoticeRequestDto requestDto) {
        Optional<Notice> notice = noticeRepository.findById(id);
        if (notice.get().getPassword().equals(requestDto.getPassword())){
            noticeService.update(id, requestDto);
            return true;
        }else{
            return false;
        }
    }
    @DeleteMapping("/api/notice/{id}")
    public Boolean deleteNotice(@PathVariable Long id,@RequestBody NoticeRequestDto requestDto) {
        Optional<Notice> notice = noticeRepository.findById(id);
        if (notice.get().getPassword().equals(requestDto.getPassword())){
            noticeRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
    @GetMapping("/api/notice/{id}")
    public Optional<Notice> getNoticeOne(@PathVariable Long id){
        return noticeRepository.findById(id);
    }
}
