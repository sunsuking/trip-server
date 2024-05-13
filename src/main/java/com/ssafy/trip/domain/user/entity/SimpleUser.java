package com.ssafy.trip.domain.user.entity;

import lombok.Data;

@Data
public class SimpleUser {
    private Long userId;
    private String nickname;
    private String profileImage;
}
