package com.vic.lovelytrip.dto;

import lombok.Data;

@Data
public class ImageDetail {

    private long id;
    private int reference_table;  //CHECK(reference_table IN (0, 1, 2)) -- 0: user, 1: trip, 2: tour_group
    private long reference_id;
    private String imageUrl;
    private String imageZone;
    private int displayOrder;

}
