package com.ssafy.trip.domain.tour.entity;

import lombok.Data;

@Data
public class City {
    private int cityId;
    private String name;
    private double latitude;
    private double longitude;
}
