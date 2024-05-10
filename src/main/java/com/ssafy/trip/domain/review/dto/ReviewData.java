package com.ssafy.trip.domain.review.dto;

import lombok.Data;

public class ReviewData {

    @Data
    public static class Create {
        private String title;
        private String content;
        private int tourId;
        private int authorId;
    }

    @Data
    public static class Update {
        private String title;
        private String content;
        private int tourId;
        private Long reviewId;
    }
}
