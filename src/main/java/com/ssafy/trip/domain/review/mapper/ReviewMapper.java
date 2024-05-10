package com.ssafy.trip.domain.review.mapper;

import com.ssafy.trip.domain.review.dto.ReviewData;
import com.ssafy.trip.domain.review.dto.ReviewData.Create;
import com.ssafy.trip.domain.review.dto.ReviewData.Update;
import com.ssafy.trip.domain.review.entity.Review;
import org.apache.ibatis.annotations.Mapper;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Mapper
public interface ReviewMapper {
    // SELECT
    List<Review> getReviews();

    Optional<Review> findById(long reviewId);


    // List<Comment> getComments();
    // Optional<Comment> findById(long commentId);
    // void saveComment(Comment comment);
    // void updateComment(Comment comment);
    // void deleteComment(long commentId);
    //
    // INSERT
    void saveReview(Create create);

    // UPDATE
    void updateReview(Update update);

    // DELETE
    void deleteReview(long reviewId);

    boolean existsLikeByReviewIdAndUserId(long reviewId, long userId);

    void saveLike(long reviewId, long userId);

    void deleteLike(long reviewId, long userId);
}
