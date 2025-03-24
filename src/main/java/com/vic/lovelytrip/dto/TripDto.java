package com.vic.lovelytrip.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TripDto extends BaseDto {

    private String title;
    private String description;
    private String destination;
    private int duration;

}
