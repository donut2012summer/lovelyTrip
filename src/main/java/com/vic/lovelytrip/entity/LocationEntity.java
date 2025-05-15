package com.vic.lovelytrip.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

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
