package com.ssafy.trip.domain.review.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.trip.domain.review.entity.ReviewWithUser;
import com.ssafy.trip.domain.user.entity.SimpleUser;
import lombok.Data;

import java.util.List;

public class ReviewData {

    @Data
    public static class Create {
        private Long reviewId;
        private String content;
        private int tourId;
    }

    @Data
    public static class Update {
        private String content;
        private int tourId;
        private Long reviewId;
    }

    @Data
    public static class SimpleReview {
        private Long reviewId;
        private String address;
        private String content;
        @JsonProperty("isLiked")
        private boolean isLiked;
        private int tourId;
        private String createdAt;
        private List<String> images;
        private SimpleUser user;

        public static SimpleReview of(ReviewWithUser review) {
            SimpleReview simpleReview = new SimpleReview();
            simpleReview.setReviewId(review.getReviewId());
            simpleReview.setContent(review.getContent());
            simpleReview.setLiked(review.isLiked());
            simpleReview.setAddress(review.getAddress());
            simpleReview.setTourId(review.getTourId());
            simpleReview.setCreatedAt(review.getCreatedAt().toString());
            simpleReview.setImages(review.getImages());
            simpleReview.setUser(review.getUser());
            return simpleReview;
        }
    }
}
