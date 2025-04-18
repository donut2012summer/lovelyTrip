package com.vic.lovelytrip.dto;

import lombok.Data;

import java.util.List;

@Data
public class TripDto extends BaseDto {

    private String title;
    private String description;
    private String destination;
    private int duration;
    private long supplierId;

    private List<TourGroupDto> tourGroupDtoList;

}
