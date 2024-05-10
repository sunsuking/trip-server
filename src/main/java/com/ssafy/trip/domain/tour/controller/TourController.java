package com.ssafy.trip.domain.tour.controller;

import com.ssafy.trip.core.response.SuccessResponse;
import com.ssafy.trip.domain.tour.dto.TourData;
import com.ssafy.trip.domain.tour.entity.Category;
import com.ssafy.trip.domain.tour.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/tour")
@RequiredArgsConstructor
public class TourController {
    private final TourService tourService;

    @GetMapping("/search")
    public SuccessResponse<List<TourData.Search>> searchKeyword(
            @RequestParam(value = "city") String city,
            @RequestParam(value = "query", required = false) String query
    ) {
        if (!StringUtils.hasText(query)) {
            return SuccessResponse.of(List.of());
        }

        List<TourData.Search> tours = tourService.findTourByKeyword(city, query);
        return SuccessResponse.of(tours);
    }

    @GetMapping("/category")
    public SuccessResponse<List<Category>> categories() {
        return SuccessResponse.of(tourService.getCategories());
    }
}
