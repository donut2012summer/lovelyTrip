package com.vic.lovelytrip.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;


import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "tour_group")
@Data
public class TourGroupEntity extends BaseEntity{

    private long tripId; // FK in DB
    private String title;
    private String description;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private BigDecimal unitPrice;
    private int availability;
    private int status;


}
