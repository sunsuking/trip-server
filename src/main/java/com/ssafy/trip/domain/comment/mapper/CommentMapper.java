package com.ssafy.trip.domain.comment.mapper;

import com.ssafy.trip.domain.comment.dto.CommentData.Create;
import com.ssafy.trip.domain.comment.dto.CommentData.Detail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    void save(Create create);

    void delete(Long commentId);

    void update();

    List<Detail> list(Long reviewId);

    List<Detail> listByUserId(Long userId);
}
