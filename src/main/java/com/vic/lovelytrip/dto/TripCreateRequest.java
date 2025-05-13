package com.vic.lovelytrip.dto;

import lombok.Data;

import java.util.List;

@Data
public class TripCreateRequest {

    private String title;
    private String description;
    private long mainLocationId;
    private int minDuration;
    private long supplierId;
    private int status;

    private List<ImageCreateRequest> imageCreateRequestList;

}
