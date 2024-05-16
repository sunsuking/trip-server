package com.ssafy.trip.domain.review.service;

import com.ssafy.trip.core.entity.CustomPage;
import com.ssafy.trip.domain.review.dto.ReviewData;
import com.ssafy.trip.domain.review.dto.ReviewData.Create;
import com.ssafy.trip.domain.review.dto.ReviewData.SimpleReview;
import com.ssafy.trip.domain.review.dto.ReviewData.Update;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    CustomPage<SimpleReview> getReviews(Pageable pageable, Long userId);

    Optional<SimpleReview> findById(long reviewId);

    List<SimpleReview> getReviewsFindById(Long userId);

    boolean existsLikeByReviewIdAndUserId(long reviewId, long userId);

    void saveReview(Create create, Long userId, List<MultipartFile> images);

    void saveImg(Long reviewId, String[] imgUrls);

    void saveLike(long reviewId, long userId);

    void updateReview(Update update);

    void deleteReview(long reviewId);

    void deleteLike(long reviewId, long userId);

    List<SimpleReview> getLikedReviews(Long userId);
}
