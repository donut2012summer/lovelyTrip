package com.vic.lovelytrip.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "image")
@Data
public class ImageEntity extends BaseEntity {
    private int referenceTable;  //CHECK(reference_table IN (0, 1, 2)) -- 0: user, 1: trip, 2: tour_group
    private long referenceId;
    private String imageUrl;
    private String imageZone;
    private String storedFilename;
    private int displayOrder;
    private int status;
}
