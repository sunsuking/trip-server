package com.ssafy.trip.domain.comment.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Comment {
    private Long commentId;
    private Long reviewId;
    private Long userId;
    private String content;
    private String createdAt;
    private String updatedAt;
}
