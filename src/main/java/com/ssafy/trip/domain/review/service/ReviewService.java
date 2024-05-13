package com.ssafy.trip.domain.review.service;

import com.ssafy.trip.core.entity.CustomPage;
import com.ssafy.trip.domain.review.dto.ReviewData;
import com.ssafy.trip.domain.review.dto.ReviewData.CommentCreate;
import com.ssafy.trip.domain.review.dto.ReviewData.Create;
import com.ssafy.trip.domain.review.dto.ReviewData.Update;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    CustomPage<ReviewData.SimpleReview> getReviews(Pageable pageable, Long userId);

    Optional<ReviewData.Review> findById(Long reviewId, Long userId);

    List<ReviewData.CommentResponse> findByReviewId(Long reviewId);

    void saveComment(Long reviewId, CommentCreate create, Long userId);

    void saveReview(Create create, Long userId, List<MultipartFile> images);


    void saveLike(long reviewId, long userId);

    void updateReview(Update update);

    void deleteReview(long reviewId);

    void deleteLike(long reviewId, long userId);
}
