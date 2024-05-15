package com.ssafy.trip.domain.tour.mapper;

import com.ssafy.trip.domain.tour.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TourMapper {
    List<Tour> findAll();

    List<Category> findCategories();

    Tour findById(int tourId);

    TourWithContent findWithContentById(int tourId);

    List<TourWithContent> findWithContentByKeyword(int city, String keyword);

    List<City> findCities();

    List<Town> findTowns(int cityCode);
}
