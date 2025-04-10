package com.vic.lovelytrip.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "image")
@Data
public class ImageEntity extends BaseEntity {
    private int reference_table;  //CHECK(reference_table IN (0, 1, 2)) -- 0: user, 1: trip, 2: tour_group
    private long reference_id;
    private String imageUrl;
    private String imageZone;
    private int displayOrder;
}
