package com.ssafy.trip.domain.notice.service;

import com.ssafy.trip.domain.notice.entity.Notice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface NoticeService {
    // 공지사항 전체 조회
    List<Notice> findNotice();

    // 공지사항 단건 조회
    Optional<Notice> findByTitle(Long noticeId);

    // 공지사항 등록
    void save(Notice notice);

    // 공지사항 수정
    void update(Notice notice, Long noticeId);

    // 공지사항 삭제
    void delete(Long noticeId);
}
