package com.ssafy.trip.domain.notice.service;

import com.ssafy.trip.domain.notice.entity.Notice;
import com.ssafy.trip.domain.notice.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{

    private final NoticeMapper noticeMapper;

    @Override
    public List<Notice> findNotices() {
        return noticeMapper.findNotices();
    }

    @Override
    public Optional<Notice> findNoticeById(Long noticeId) {
        return noticeMapper.findNoticeById(noticeId);
    }

    @Override
    public void save(Notice notice) {
        noticeMapper.save(notice);
    }

    @Override
    public void update(Notice notice, Long noticeId) {
        noticeMapper.update(notice, noticeId);
    }

    @Override
    public void delete(Long noticeId) {
        noticeMapper.delete(noticeId);
    }
}
