package com.vic.lovelytrip.dto;

import lombok.Data;

@Data
public class ImageCreateRequest{
    private String imageUrl;
    private String imageZone;
    private int displayOrder;
}
