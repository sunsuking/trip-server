package com.ssafy.trip.domain.notice.controller;

import com.ssafy.trip.domain.notice.entity.Notice;
import com.ssafy.trip.domain.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/notice")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    // 공지사항 전체 조회
    @GetMapping("/list")
    public ResponseEntity<List<Notice>> notice() {
        List<Notice> list = noticeService.findNotice();
        if (list != null && !list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    // 공지사항 단건 조회
    @GetMapping("/view/{noticeId}")
    public ResponseEntity<Optional<Notice>> searchByTitle(@PathVariable Long noticeId) {
        Optional<Notice> notice = noticeService.findByTitle(noticeId);
        return new ResponseEntity<>(notice, HttpStatus.OK);
    }

    // 공지사항 등록
    @PostMapping("/create")
    public void create(@RequestBody Notice noticeDto) {
        noticeService.save(noticeDto);
    }

    // 공지사항 수정
    @PutMapping("/modify/{noticeId}")
    public void modify(@RequestBody Notice noticeDto, @PathVariable Long noticeId) {
        noticeService.update(noticeDto, noticeId);
    }

    // 공지사항 삭제
    @DeleteMapping("/delete/{noticeId}")
    public void delete(@PathVariable Long noticeId) {
        noticeService.delete(noticeId);
    }

}
