package com.ssafy.trip.domain.social.controller;

import com.ssafy.trip.core.response.SuccessResponse;
import com.ssafy.trip.domain.social.dto.SocialData.Create;
import com.ssafy.trip.domain.social.dto.SocialData.Update;
import com.ssafy.trip.domain.social.entity.Social;
import com.ssafy.trip.domain.social.service.SocialService;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/social")
@RequiredArgsConstructor
public class SocialController {
    private final SocialService socialService;
    private Logger log = LoggerFactory.getLogger(getClass());


    // 게시글 전체 조회
    @GetMapping("/list")
    public ResponseEntity<List<Social>> getSocialList() {
        return new ResponseEntity<>(socialService.findSocials(), HttpStatus.OK);
    }

    // 게시글 단건 조회
    @GetMapping("/{socialId}")
    public ResponseEntity<Optional<Social>> getSocialBySocialId(@PathVariable Long socialId) {
        return new ResponseEntity<>(socialService.findSocialById(socialId), HttpStatus.OK);
    }

    // 게시글 등록
    @PostMapping("/create")
    public ResponseEntity<Void> save(@RequestBody Create create) {
        socialService.save(create);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 게시글 수정
    @PutMapping("/modify/{socialId}")
    public ResponseEntity<Void> modify(@RequestBody Update update) {
        socialService.update(update);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 게시글 삭제
    @DeleteMapping("/delete/{socialId}")
    public ResponseEntity<Void> delete(@PathVariable Long socialId) {
        socialService.delete(socialId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
