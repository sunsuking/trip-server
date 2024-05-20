package com.ssafy.trip.domain.home.mapper;

import com.ssafy.trip.domain.review.entity.Review;
import com.ssafy.trip.domain.tour.entity.Tour;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface HomeMapper {
    // 리뷰 총 개수 조회
    Long getTotalNumberOfReviews();

    // 공지사항 총 개수 조회
    Long getTotalNumberOfNotices();

    // 총 사용자 수 조회
    Long getTotalNumberOfUsers();

    // 총 여행지 수 조회
    Long getTotalNumberOftours();

    // 생성된 여행 계획 수 조회
    Long getTotalNumberOfPlans();

    // Top 여행지 조회
    List<Tour> getTopTours();

    // Top 리뷰 조회
    List<Review> getTopReviews();
}
