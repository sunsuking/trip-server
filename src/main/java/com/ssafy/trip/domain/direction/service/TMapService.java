package com.ssafy.trip.domain.direction.service;

import com.ssafy.trip.domain.direction.dto.DirectionData.Request;
import com.ssafy.trip.domain.direction.dto.DirectionData.RequestToLambda;
import com.ssafy.trip.domain.direction.entity.Direction;
import com.ssafy.trip.domain.direction.entity.Vehicle;
import com.ssafy.trip.domain.direction.mapper.DirectionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Slf4j
@RequiredArgsConstructor
public class TMapService {
    private final DirectionMapper directionMapper;

    @Value("${AWS_LAMBDA_FUNCTION_URL}")
    private String baseURL;

    private List<Vehicle> parseAndSave(RequestToLambda request, int directionId) {
        URI uri = UriComponentsBuilder
                .fromUriString(baseURL)
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Vehicle>> response = restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(request), new ParameterizedTypeReference<List<Vehicle>>() {
        });

        System.out.println(response.getBody());

        Objects.requireNonNull(response.getBody()).forEach(vehicle -> {
            vehicle.setDirectionId(directionId);
            directionMapper.saveVehicle(vehicle);
            vehicle.getSteps().forEach(step -> {
                step.setVehicleId(vehicle.getVehicleId());
                directionMapper.saveStep(step);
            });
        });

        return response.getBody();
    }

    public List<Vehicle> getDirection(Request request) {
        AtomicBoolean isNew = new AtomicBoolean(false);
        Direction direction = directionMapper.findById(request.getStartTourId(), request.getEndTourId()).orElseGet(() -> {
            isNew.set(true);
            Direction newDirection = new Direction();
            newDirection.setStartTourId(request.getStartTourId());
            newDirection.setEndTourId(request.getEndTourId());
            directionMapper.saveDirection(newDirection);
            return newDirection;
        });

        if (isNew.get()) {
            System.out.println(direction.getDirectionId());
            return parseAndSave(RequestToLambda.of(request), direction.getDirectionId());
        }

        return directionMapper.findVehicles(direction.getDirectionId());
    }
}
