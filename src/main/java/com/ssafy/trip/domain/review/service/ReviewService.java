package com.ssafy.trip.domain.review.service;

import com.ssafy.trip.domain.review.dto.ReviewData;
import com.ssafy.trip.domain.review.dto.ReviewData.Create;
import com.ssafy.trip.domain.review.dto.ReviewData.Detail;
import com.ssafy.trip.domain.review.dto.ReviewData.Update;

import java.util.List;
import java.util.Optional;

import static com.ssafy.trip.domain.review.dto.ReviewData.*;

public interface ReviewService {
    List<Detail> getReviews();

    Optional<Detail> findById(long reviewId);

    boolean existsLikeByReviewIdAndUserId(long reviewId, long userId);

    void saveReview(Create create, long authorId);

    void saveImg(Long reviewId, String[] imgUrls);

    void saveLike(long reviewId, long userId);

    void updateReview(Update update);

    void deleteReview(long reviewId);

    void deleteLike(long reviewId, long userId);

    List<Detail> search(Search search);
}
