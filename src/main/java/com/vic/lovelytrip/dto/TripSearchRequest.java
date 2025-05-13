package com.vic.lovelytrip.dto;

import lombok.Data;

@Data
public class TripSearchRequest {

    private String keyword;
    private Long destinationId;
    private Integer durationMin;
    private Integer durationMax;
    private Integer priceMin;
    private Integer priceMax;

    private Integer page = 0;
    private Integer size = 10;

}
