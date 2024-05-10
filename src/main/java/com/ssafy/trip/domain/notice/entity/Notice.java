package com.ssafy.trip.domain.notice.entity;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class Notice {
    private Long noticeId;
    private String title;
    private String content;
    private Timestamp createdAt;
}
