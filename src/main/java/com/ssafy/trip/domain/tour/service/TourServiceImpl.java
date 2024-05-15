package com.ssafy.trip.domain.tour.service;

import com.ssafy.trip.domain.tour.dto.TourData;
import com.ssafy.trip.domain.tour.entity.Category;
import com.ssafy.trip.domain.tour.entity.City;
import com.ssafy.trip.domain.tour.entity.Town;
import com.ssafy.trip.domain.tour.mapper.TourMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {
    private final TourMapper tourMapper;

    @Override
    public List<TourData.Search> findTourByKeyword(int city, String keyword) {
        return tourMapper.findWithContentByKeyword(city, keyword).stream().map(TourData.Search::of).toList();
    }

    @Override
    public List<Category> getCategories() {
        return tourMapper.findCategories();
    }

    @Override
    public List<City> getCities() {
        return tourMapper.findCities();
    }

    @Override
    public List<Town> getTowns(int cityCode) {
        return tourMapper.findTowns(cityCode);
    }
}
