package com.vic.lovelytrip.dto;

import lombok.Data;

@Data
public class TripDto extends BaseDto {

    private String title;
    private String description;
    private String destination;
    private int duration;
    private long supplierId;

}
