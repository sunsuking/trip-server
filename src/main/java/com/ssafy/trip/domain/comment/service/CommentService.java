package com.ssafy.trip.domain.comment.service;

import com.ssafy.trip.domain.comment.dto.CommentData.Create;
import com.ssafy.trip.domain.comment.dto.CommentData.Detail;

import java.util.List;

public interface CommentService {
    void save(Create create);

    List<Detail> list(Long reviewId);

    void delete(Long commentId);

    void update();

}
