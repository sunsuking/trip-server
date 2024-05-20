package com.ssafy.trip.domain.home.controller;

import com.ssafy.trip.domain.home.dto.HomeData;
import com.ssafy.trip.domain.home.service.HomeService;
import com.ssafy.trip.domain.review.entity.Review;
import com.ssafy.trip.domain.tour.entity.Tour;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/home")
public class HomeController {
    private final HomeService homeService;

    @GetMapping("")
    public ResponseEntity<HomeData> getHomeData() {
        Long reviews = homeService.getTotalNumberOfReviews();
        Long notices = homeService.getTotalNumberOfNotices();
        Long users = homeService.getTotalNumberOfUsers();
        Long tours = homeService.getTotalNumberOftours();
        Long plans = homeService.getTotalNumberOfPlans();
        List<Tour> tourList = homeService.getTopTours();
        List<Review> reviewList = homeService.getTopReviews();

        HomeData homeData = new HomeData();
        homeData.setReviewSize(reviews);
        homeData.setNoticeSize(notices);
        homeData.setUserSize(users);
        homeData.setTourSize(tours);
        homeData.setPlanSize(plans);
        homeData.setTopTours(tourList);
        homeData.setTopReviews(reviewList);

        return new ResponseEntity<>(homeData, HttpStatus.OK);
    }
}
