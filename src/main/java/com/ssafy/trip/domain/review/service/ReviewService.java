package com.ssafy.trip.domain.review.service;

import com.ssafy.trip.domain.review.dto.ReviewData;
import com.ssafy.trip.domain.review.dto.ReviewData.Create;
import com.ssafy.trip.domain.review.dto.ReviewData.Update;
import com.ssafy.trip.domain.review.entity.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<Review> getReviews();

    Optional<Review> findById(long reviewId);

    boolean existsLikeByReviewIdAndUserId(long reviewId, long userId);

    void saveReview(Create create);

    void saveLike(long reviewId, long userId);

    void updateReview(Update update);

    void deleteReview(long reviewId);

    void deleteLike(long reviewId, long userId);
}
