package com.ssafy.trip.domain.social.controller;

import com.ssafy.trip.core.response.SuccessResponse;
import com.ssafy.trip.domain.social.dto.SocialData.Create;
import com.ssafy.trip.domain.social.dto.SocialData.Update;
import com.ssafy.trip.domain.social.entity.Social;
import com.ssafy.trip.domain.social.service.SocialService;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/social")
@RequiredArgsConstructor
public class SocialController {

    private final SocialService socialService;

    // 게시글 전체 조회
    @GetMapping("/list")
    public SuccessResponse<List<Social>> getSocialList() {
        return SuccessResponse.of(socialService.findSocials());
    }

    // 게시글 단건 조회
    @GetMapping("/{socialId}")
    public SuccessResponse<Optional<Social>> getSocialBySocialId(@PathVariable Long socialId) {
        return SuccessResponse.of(socialService.findSocialById(socialId));
    }

    // 게시글 등록
    @PostMapping("/create")
    public SuccessResponse<Void> save(Create create) {
        socialService.save(create);
        return SuccessResponse.empty();
    }

    // 게시글 수정
    @PutMapping("/modify/{socialId}")
    public SuccessResponse<Void> modify(Update update) {
        socialService.update(update);
        return SuccessResponse.empty();
    }

    // 게시글 삭제
    @DeleteMapping("/delete/{socialId}")
    public SuccessResponse<Void> delete(@PathVariable Long socialId) {
        socialService.delete(socialId);
        return SuccessResponse.empty();
    }
}
