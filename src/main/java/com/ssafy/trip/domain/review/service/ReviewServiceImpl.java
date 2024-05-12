package com.ssafy.trip.domain.review.service;

import com.ssafy.trip.domain.review.dto.ReviewData;
import com.ssafy.trip.domain.review.dto.ReviewData.Create;
import com.ssafy.trip.domain.review.dto.ReviewData.Detail;
import com.ssafy.trip.domain.review.dto.ReviewData.Search;
import com.ssafy.trip.domain.review.dto.ReviewData.Update;
import com.ssafy.trip.domain.review.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewMapper reviewMapper;

    @Override
    public List<Detail> getReviews() {
        return reviewMapper.getReviews();
    }

    @Override
    public List<Detail> search(Search search) {
        log.debug("{}", search);
        return reviewMapper.search(search);
    }

    @Override
    public Optional<Detail> findById(long reviewId) {
        return reviewMapper.findById(reviewId);
    }

    @Override
    public boolean existsLikeByReviewIdAndUserId(long reviewId, long userId) {
        return reviewMapper.existsLikeByReviewIdAndUserId(reviewId, userId);
    }

    @Override
    @Transactional
    public void saveReview(Create create, long authorId) {
        reviewMapper.saveReview(create, authorId);
        saveImg(create.getReviewId(), create.getImgUrls());
    }

    @Override
    @Transactional
    public void saveImg(Long reviewId, String[] imgUrls) {
        Arrays.stream(imgUrls).forEach((imgUrl) -> reviewMapper.saveImg(reviewId,imgUrl));
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
