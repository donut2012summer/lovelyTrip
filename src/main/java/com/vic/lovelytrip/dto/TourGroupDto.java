package com.vic.lovelytrip.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TourGroupDto extends BaseDto{

    private long tripId; // FK in DB
    private String title;
    private String description;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private BigDecimal unitPrice;
    private int availability;
    private int status;

}
