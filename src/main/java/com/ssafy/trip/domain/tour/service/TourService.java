package com.ssafy.trip.domain.tour.service;

import com.ssafy.trip.domain.tour.dto.TourData;
import com.ssafy.trip.domain.tour.entity.Category;
import com.ssafy.trip.domain.tour.entity.City;

import java.util.List;

public interface TourService {
    List<TourData.Search> findTourByKeyword(int city, String keyword);

    List<Category> getCategories();

    City findCityById(int cityId);

    List<TourData.Search> findStayByCityId(int cityId);
}
