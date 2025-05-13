package com.vic.lovelytrip.dto;

import lombok.Data;

import java.util.List;

@Data
public class TripDetailResponse {

    private long id;

    private String title;
    private String description;
    private long mainLocationId;
    private int minDuration;
    // FK in DB, no orm in java
    private long supplierId;

    private int status;

    private List<ImageDetail> imageList;

    private List<TourGroupDetail> tourGroupList;

}
