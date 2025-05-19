package com.vic.lovelytrip.mapper;


import com.vic.lovelytrip.dto.ImageCreateRequest;
import com.vic.lovelytrip.dto.ImageDetail;
import com.vic.lovelytrip.entity.ImageEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ImageMapper{


    ImageEntity mapToEntity(ImageCreateRequest imageCreateRequest) {
        ImageEntity imageEntity = new ImageEntity();

        imageEntity.setImageUrl(imageCreateRequest.getImageUrl());
        imageEntity.setImageZone(imageCreateRequest.getImageZone());
        imageEntity.setDisplayOrder(imageCreateRequest.getDisplayOrder());

        return imageEntity;

    }

    public List<ImageEntity> batchMapToEntity(List<ImageCreateRequest> imageCreateRequestList){

        return Optional.ofNullable(imageCreateRequestList)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new IllegalArgumentException("ImageList cannot be null or empty!"))
                .stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());

    }
    public ImageDetail mapToImageDetail(ImageEntity imageEntity) {
        ImageDetail imageDetail = new ImageDetail();

        imageDetail.setId(imageEntity.getId());
        imageDetail.setImageUrl(imageEntity.getImageUrl());
        imageDetail.setImageZone(imageEntity.getImageZone());
        imageDetail.setDisplayOrder(imageEntity.getDisplayOrder());

        return imageDetail;
    }

    public List<ImageDetail> batchMapToImageDetail(List<ImageEntity> imageEntityList) {
        List<ImageDetail> imageDetailList = new ArrayList<>();

        if (imageEntityList != null && !imageEntityList.isEmpty()) {
            for (ImageEntity imageEntity : imageEntityList) {
                imageDetailList.add(mapToImageDetail(imageEntity));
            }
        }

        return imageDetailList;
    }

}
