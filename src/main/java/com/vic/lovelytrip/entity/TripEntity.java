package com.vic.lovelytrip.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "trip")
@Data
public class TripEntity extends BaseEntity {

    private String title;
    private String description;
    private long mainLocationId;
    private int minDuration;
    // FK in DB, no orm in java
    private long supplierId;

    private int status;

}
