package com.sparta.springcore.repository;

import com.sparta.springcore.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findAllByOrderByModifiedAtDesc();
    List<Notice> findAllByUserId(Long userId);
}

