package com.ssafy.trip.domain.home.dto;

import com.ssafy.trip.domain.review.entity.Review;
import com.ssafy.trip.domain.tour.entity.Tour;
import lombok.Data;

import java.util.List;

@Data
public class HomeData {
    private Long reviewSize;
    private Long noticeSize;
    private Long userSize;
    private Long tourSize;
    private Long planSize;
    private List<Tour> topTours;
    private List<Review> topReviews;
}
