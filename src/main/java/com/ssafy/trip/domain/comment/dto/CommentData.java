package com.ssafy.trip.domain.comment.dto;

import lombok.Data;

public class CommentData {
    @Data
    public static class Create {
        private Long reviewId;
        private Long userId;
        private String content;
    }

    @Data
    public static class Detail{
        private Long commentId;
        private Long userId;
        private String nickname;
        private String content;
        private String createdAt;
        private String profileImage;
    }

    @Data
    public static class SimpleDetail {
        private Long commentId;
        private Long reviewId;
        private String createdAt;
        private String content;
        private String simpleReviewContent;
    }
}
