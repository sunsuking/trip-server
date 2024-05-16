package com.ssafy.trip.domain.review.mapper;

import com.ssafy.trip.domain.review.dto.ReviewData;
import com.ssafy.trip.domain.review.dto.ReviewData.Create;
import com.ssafy.trip.domain.review.dto.ReviewData.SimpleReview;
import com.ssafy.trip.domain.review.dto.ReviewData.Update;
import com.ssafy.trip.domain.review.entity.ReviewWithUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ReviewMapper {
    // SELECT
    List<ReviewWithUser> findReviews(List<Long> reviewIds, long userId);

    List<Long> findPagingIds(Pageable pageable);

    List<ReviewWithUser> findAllById(Long userId);

    int countReviews();

    Optional<ReviewWithUser> findById(long reviewId);

    // INSERT
    Long saveReview(Create create, long authorId);

    // UPDATE
    void updateReview(Update update);

    // DELETE
    void deleteReview(long reviewId);

    boolean existsLikeByReviewIdAndUserId(long reviewId, long userId);

    void saveLike(long reviewId, long userId);

    void deleteLike(long reviewId, long userId);

    void saveImg(Long reviewId, String imgUrl);


}
