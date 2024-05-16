package com.ssafy.trip.domain.comment.service;

import com.ssafy.trip.domain.comment.dto.CommentData;
import com.ssafy.trip.domain.comment.dto.CommentData.Create;
import com.ssafy.trip.domain.comment.dto.CommentData.Detail;
import com.ssafy.trip.domain.comment.dto.CommentData.SimpleDetail;

import java.util.List;

public interface CommentService {
    void save(Create create);

    List<Detail> list(Long reviewId);

    List<Detail> listByUserId(Long userId);

    void delete(Long commentId);

    void update();


    List<SimpleDetail> simpleCommentListByUserId(Long userId);
}
