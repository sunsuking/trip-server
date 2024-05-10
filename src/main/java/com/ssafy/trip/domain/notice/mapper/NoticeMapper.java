package com.ssafy.trip.domain.notice.mapper;

import com.ssafy.trip.domain.notice.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface NoticeMapper {

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
