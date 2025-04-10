package com.vic.lovelytrip.dto;

import lombok.Data;

@Data
public class ReviewDto extends BaseDto{
    private long tripId; // FK
    private long userId; // FK
    private double rating;
    private String comment;
}
