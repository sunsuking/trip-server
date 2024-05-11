package com.ssafy.trip.domain.notice.controller;

import com.ssafy.trip.core.response.SuccessResponse;
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
    public SuccessResponse<List<Notice>> notice() {
        return SuccessResponse.of(noticeService.findNotices());
    }

    // 공지사항 단건 조회
    @GetMapping("/view/{noticeId}")
    public SuccessResponse<Optional<Notice>> searchByTitle(@PathVariable Long noticeId) {
        return SuccessResponse.of(noticeService.findNoticeById(noticeId));
    }

    // 공지사항 등록
    @PostMapping("/create")
    public SuccessResponse<Void> create(@RequestBody Notice noticeDto) {
        noticeService.save(noticeDto);
        return SuccessResponse.empty();
    }

    // 공지사항 수정
    @PutMapping("/modify/{noticeId}")
    public SuccessResponse<Void> modify(@RequestBody Notice noticeDto, @PathVariable Long noticeId) {
        noticeService.update(noticeDto, noticeId);
        return SuccessResponse.empty();
    }

    // 공지사항 삭제
    @DeleteMapping("/delete/{noticeId}")
    public SuccessResponse<Void> delete(@PathVariable Long noticeId) {
        noticeService.delete(noticeId);
        return SuccessResponse.empty();
    }

}
