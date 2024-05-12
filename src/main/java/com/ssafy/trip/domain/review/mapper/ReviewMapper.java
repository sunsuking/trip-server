package com.ssafy.trip.domain.review.mapper;

import com.ssafy.trip.domain.review.dto.ReviewData;
import com.ssafy.trip.domain.review.dto.ReviewData.Create;
import com.ssafy.trip.domain.review.dto.ReviewData.Detail;
import com.ssafy.trip.domain.review.dto.ReviewData.Search;
import com.ssafy.trip.domain.review.dto.ReviewData.Update;
import com.ssafy.trip.domain.review.entity.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ReviewMapper {
    // SELECT
    List<Detail> getReviews();

    List<Detail> search(Search search);

    Optional<Detail> findById(long reviewId);

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
