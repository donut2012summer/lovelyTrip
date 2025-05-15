package com.vic.lovelytrip.dto;

import lombok.Data;

@Data
public class ImageDetail {

    private long id;
    private int referenceTable;  //CHECK(reference_table IN (0, 1, 2)) -- 0: user, 1: trip, 2: tour_group
    private long referenceId;
    private String imageUrl;
    private String imageZone;
    private int displayOrder;

}
