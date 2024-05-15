package com.ssafy.trip.domain.review.service;

import com.ssafy.trip.core.entity.CustomPage;
import com.ssafy.trip.core.service.S3UploadService;
import com.ssafy.trip.domain.review.dto.ReviewData.Create;
import com.ssafy.trip.domain.review.dto.ReviewData.SimpleReview;
import com.ssafy.trip.domain.review.dto.ReviewData.Update;
import com.ssafy.trip.domain.review.entity.ReviewWithUser;
import com.ssafy.trip.domain.review.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewMapper reviewMapper;
    private final S3UploadService s3UploadService;

    @Override
    public CustomPage<SimpleReview> getReviews(Pageable pageable, Long userId) {
        List<Long> pagingIds = reviewMapper.findPagingIds(pageable);
        System.out.println(pagingIds);
        List<ReviewWithUser> reviews = reviewMapper.findReviews(pagingIds, userId);
        int count = reviewMapper.countReviews();
        Page<SimpleReview> pageReviews = new PageImpl<>(reviews.stream().map(SimpleReview::of).toList(), pageable, count);
        return CustomPage.of(pageReviews.getContent(), pageReviews.getNumber(), !pageReviews.isLast());
    }

    @Override
    public Optional<SimpleReview> findById(long reviewId) {
        return reviewMapper.findById(reviewId).map(SimpleReview::of);
    }

    @Override
    public List<SimpleReview> getReviewsFindById(Long userId) {
        List<ReviewWithUser> allById = reviewMapper.findAllById(userId);
        return allById.stream().map(SimpleReview::of).collect(Collectors.toList());
    }

    @Override
    public boolean existsLikeByReviewIdAndUserId(long reviewId, long userId) {
        return reviewMapper.existsLikeByReviewIdAndUserId(reviewId, userId);
    }

    @Override
    @Transactional
    public void saveReview(Create create, Long userId, List<MultipartFile> images) {
        reviewMapper.saveReview(create, userId);
        images.stream().map((image) -> {
            CompletableFuture<String> future = s3UploadService.upload(image);
            return future.join();
        }).forEach((imgUrl) -> reviewMapper.saveImg(create.getReviewId(), imgUrl));
    }

    @Override
    @Transactional
    public void saveImg(Long reviewId, String[] imgUrls) {
        Arrays.stream(imgUrls).forEach((imgUrl) -> reviewMapper.saveImg(reviewId, imgUrl));
    }

    @Override
    @Transactional
    public void saveLike(long reviewId, long userId) {
        reviewMapper.saveLike(reviewId, userId);
    }

    @Override
    @Transactional
    public void updateReview(Update update) {
        reviewMapper.updateReview(update);
    }

    @Override
    @Transactional
    public void deleteReview(long reviewId) {
        reviewMapper.deleteReview(reviewId);
    }

    @Override
    @Transactional
    public void deleteLike(long reviewId, long userId) {
        reviewMapper.deleteLike(reviewId, userId);
    }
}
