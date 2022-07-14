package com.example.blog.repository;


import com.example.blog.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findAllByOrderByModifiedAtDesc();
}
