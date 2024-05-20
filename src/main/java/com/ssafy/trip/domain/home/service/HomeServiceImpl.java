package com.ssafy.trip.domain.home.service;

import com.ssafy.trip.domain.home.mapper.HomeMapper;
import com.ssafy.trip.domain.review.entity.Review;
import com.ssafy.trip.domain.tour.entity.Tour;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService{

    public final HomeMapper homeMapper;

    @Override
    public Long getTotalNumberOfReviews() {
        return homeMapper.getTotalNumberOfReviews();
    }

    @Override
    public Long getTotalNumberOfNotices() {
        return homeMapper.getTotalNumberOfNotices();
    }

    @Override
    public Long getTotalNumberOfUsers() {
        return homeMapper.getTotalNumberOfUsers();
    }

    @Override
    public Long getTotalNumberOftours() {
        return homeMapper.getTotalNumberOftours();
    }

    @Override
    public Long getTotalNumberOfPlans() {
        return homeMapper.getTotalNumberOfPlans();
    }

    @Override
    public List<Tour> getTopTours() {
        return homeMapper.getTopTours();
    }

    @Override
    public List<Review> getTopReviews() {
        return homeMapper.getTopReviews();
    }
}
