package com.vic.lovelytrip.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "trip")
@Data
public class TripEntity extends BaseEntity {

    private String title;
    private String description;
    private String destination;
    private int duration;
    // FK in DB, no orm in java
    private long supplierId;

    @Transient
    private List<TourGroupEntity> tourGroupEntityList;

}
