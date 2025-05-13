package com.vic.lovelytrip.dto;

import lombok.Data;

@Data
public class TripSearchResponse {

    private long tripId;
    private String title;
    private String description;

    private double unitPriceMin;
    private String imageUrl;

    private long locationId;
    // FK in DB, no orm in java

    private String locationName;
}
