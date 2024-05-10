package com.ssafy.trip.domain.social.mapper;

import com.ssafy.trip.domain.social.dto.SocialData;
import com.ssafy.trip.domain.social.entity.Social;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SocialMapper {

    // 게시글 전체 조회
    List<Social> findSocials();

    // 게시글 단건 조회
    Optional<Social> findSocialById(long socialId);

    // 게시글 등록
    void save(SocialData.Create create);

    // 게시글 수정
    void update(SocialData.Update update);

    // 게시글 삭제
    void delete(Long socialId);
}
