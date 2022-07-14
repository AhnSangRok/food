package com.notice.noticeboard.repository;

import com.notice.noticeboard.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findAllByOrderByModifiedAtDesc();
}
