package com.ssafy.trip.domain.social.service;

import com.ssafy.trip.domain.social.dto.SocialData;
import com.ssafy.trip.domain.social.entity.Social;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

import static com.ssafy.trip.domain.social.dto.SocialData.*;

public interface SocialService {

    // 게시글 전체 조회
    List<Social> findSocials();

    // 게시글 단건 조회
    Optional<Social> findSocialById(long socialId);

    // 게시글 등록
    void save(Create create);

    // 게시글 수정
    void update(Update update);

    // 게시글 삭제
    void delete(Long socialId);
}
