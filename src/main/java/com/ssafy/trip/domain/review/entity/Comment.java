package com.ssafy.trip.domain.review.entity;

import com.ssafy.trip.domain.user.entity.SimpleUser;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private Long commentId;
    private SimpleUser user;
    private String content;
    private LocalDateTime createdAt;
}
