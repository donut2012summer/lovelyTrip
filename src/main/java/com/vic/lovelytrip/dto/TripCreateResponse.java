package com.vic.lovelytrip.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class TripCreateResponse{

    private long id;

    private OffsetDateTime createdTime;
}
