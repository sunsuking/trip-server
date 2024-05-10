package com.ssafy.trip.domain.social.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Social {
    private Long socialId;
    private Long userId;
    private String title;
    private String content;
    private int viewCount;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
