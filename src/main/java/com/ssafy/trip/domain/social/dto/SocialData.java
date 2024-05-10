package com.ssafy.trip.domain.social.dto;

import lombok.Data;

public class SocialData {

    @Data
    public static class Create {
        private Long userId;
        private String title;
        private String content;
    }

    @Data
    public static class Update {
        private Long socialId;
        private String title;
        private String content;
    }
}
