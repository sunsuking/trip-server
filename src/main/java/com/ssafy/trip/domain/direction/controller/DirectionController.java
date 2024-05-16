package com.ssafy.trip.domain.direction.controller;

import com.ssafy.trip.domain.direction.dto.DirectionData;
import com.ssafy.trip.domain.direction.entity.Vehicle;
import com.ssafy.trip.domain.direction.service.TMapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/direction")
public class DirectionController {
    private final TMapService tMapService;

    @PostMapping("")
    public List<Vehicle> getVehicle(@RequestBody DirectionData.Request request) {
        return tMapService.getDirection(request);
    }
}
