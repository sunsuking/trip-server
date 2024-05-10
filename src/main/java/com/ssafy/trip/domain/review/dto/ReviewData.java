package com.ssafy.trip.domain.review.dto;

import lombok.Data;

import java.util.List;

public class ReviewData {

    @Data
    public static class Create {
        private Long reviewId;
        private String title;
        private String content;
        private int tourId;
        private String[] imgUrls;
    }

    @Data
    public static class Update {
        private String title;
        private String content;
        private int tourId;
        private Long reviewId;
    }

    // TODO: 유저 Profile에서 사진도 같이 가져오기
    @Data
    public static class Detail {
        private Long reviewId;
        private String title;
        private String content;
        private String authorName;
        private String authorEmail;
        private int tourId;
        private String createdAt;
        private String updatedAt;
        private int likeCount;
        private List<String> imgUrls;
    }
}
