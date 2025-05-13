package com.vic.lovelytrip.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "location")
public class LocationEntity extends BaseEntity {

    private String name;
    private int type;
    private int parentId;
    private double latitude;
    private double longitude;
    private int status;

}
