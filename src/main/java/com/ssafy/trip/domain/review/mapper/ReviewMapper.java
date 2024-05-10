package com.ssafy.trip.domain.review.mapper;

import com.ssafy.trip.domain.review.dto.ReviewData;
import com.ssafy.trip.domain.review.dto.ReviewData.Create;
import com.ssafy.trip.domain.review.dto.ReviewData.Detail;
import com.ssafy.trip.domain.review.dto.ReviewData.Update;
import com.ssafy.trip.domain.review.entity.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ReviewMapper {
    // SELECT
    List<Detail> getReviews();

    Optional<Detail> findById(long reviewId);


    // List<Comment> getComments();
    // Optional<Comment> findById(long commentId);
    // void saveComment(Comment comment);
    // void updateComment(Comment comment);
    // void deleteComment(long commentId);
    //
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
