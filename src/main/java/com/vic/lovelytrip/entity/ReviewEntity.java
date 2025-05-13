package com.vic.lovelytrip.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "review")
@Data
public class ReviewEntity extends BaseEntity {
    private long tripId; // FK
    private long userId; // FK
    private double rating;
    private String comment;
}
