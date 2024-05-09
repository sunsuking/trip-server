package com.ssafy.trip.domain.review.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Review {
    private Long reviewId;
    private String title;
    private String content;
    private String authorId;
    private int tourId;
    private String createdAt;
    private String updatedAt;
    private int likeCount;
}
