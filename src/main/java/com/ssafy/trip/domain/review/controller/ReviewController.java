package com.ssafy.trip.domain.review.controller;

import com.ssafy.trip.core.annotation.CurrentUser;
import com.ssafy.trip.core.response.SuccessResponse;
import com.ssafy.trip.domain.review.dto.ReviewData.Create;
import com.ssafy.trip.domain.review.dto.ReviewData.Update;
import com.ssafy.trip.domain.review.entity.Review;
import com.ssafy.trip.domain.review.service.ReviewService;
import com.ssafy.trip.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    public SuccessResponse<List<Review>> list() {
        return SuccessResponse.of(reviewService.getReviews());
    }

    @GetMapping("/{id}")
    public SuccessResponse<Review> view(@PathVariable("id") Long id) {
        return SuccessResponse.of(
                reviewService.findById(id)
                        .orElseThrow(() -> new RuntimeException("존재하지 않는 여행 후기입니다.")));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SuccessResponse<Void> create(@RequestBody Create create) {
        log.debug("{}", create);
        reviewService.saveReview(create);
        return SuccessResponse.empty();
    }

    @PutMapping
    public SuccessResponse<Void> update(@RequestBody Update update) {
        reviewService.updateReview(update);
        return SuccessResponse.empty();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public SuccessResponse<Void> delete(@PathVariable("id") Long id) {
        reviewService.deleteReview(id);
        return SuccessResponse.empty();
    }

    @PostMapping("like/{id}")
    public SuccessResponse<Void> like(@PathVariable("id") Long reviewId, @CurrentUser User user) {
        reviewService.saveLike(reviewId, user.getUserId());
        return SuccessResponse.empty();
    }

    @DeleteMapping("like/{id}")
    public SuccessResponse<Void> unLike(@PathVariable("id") Long reviewId, @CurrentUser User user) {
        reviewService.deleteLike(reviewId, user.getUserId());
        return SuccessResponse.empty();
    }
}
