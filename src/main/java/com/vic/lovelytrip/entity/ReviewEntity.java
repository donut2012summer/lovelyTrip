package com.vic.lovelytrip.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;


@Table(name = "review")
@Data
public class ReviewEntity extends BaseEntity {
    private long tripId; // FK
    private long userId; // FK
    private double rating;
    private String comment;
}
