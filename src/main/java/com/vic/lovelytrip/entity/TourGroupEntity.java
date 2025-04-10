package com.vic.lovelytrip.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
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
