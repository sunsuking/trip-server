package com.ssafy.trip.domain.review.controller;

import com.ssafy.trip.core.annotation.CurrentUser;
import com.ssafy.trip.core.entity.CustomPage;
import com.ssafy.trip.core.response.SuccessResponse;
import com.ssafy.trip.core.service.S3UploadService;
import com.ssafy.trip.domain.review.dto.ReviewData;
import com.ssafy.trip.domain.review.dto.ReviewData.Create;
import com.ssafy.trip.domain.review.dto.ReviewData.Update;
import com.ssafy.trip.domain.review.service.ReviewService;
import com.ssafy.trip.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final S3UploadService s3UploadService;

    @GetMapping("")
    public SuccessResponse<CustomPage<ReviewData.SimpleReview>> getReviews(
            @PageableDefault(size = 12) Pageable pageable,
            @CurrentUser User user
    ) {
        Long userId = user == null ? 0 : user.getUserId();
        return SuccessResponse.of(reviewService.getReviews(pageable, userId));

    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public SuccessResponse<Void> create(
            Create create,
            @CurrentUser User user,
            @RequestParam("images") List<MultipartFile> images
    ) {
        reviewService.saveReview(create, user.getUserId(), images);
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

    @PostMapping("/{id}/like")
    public SuccessResponse<Void> like(@PathVariable("id") Long reviewId, @CurrentUser User user) {
        reviewService.saveLike(reviewId, user.getUserId());
        return SuccessResponse.empty();
    }

    @DeleteMapping("/{id}/like")
    public SuccessResponse<Void> unLike(@PathVariable("id") Long reviewId, @CurrentUser User user) {
        reviewService.deleteLike(reviewId, user.getUserId());
        return SuccessResponse.empty();
    }

    @GetMapping("/{userId}")
    public SuccessResponse<List<ReviewData.SimpleReview>> getReviews(@PathVariable("userId")Long userId) {
        log.debug("{}", userId);
        return SuccessResponse.of(reviewService.getReviewsFindById(userId));
    }
}
