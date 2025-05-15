package com.vic.lovelytrip.mapper;

import com.vic.lovelytrip.dto.*;
import com.vic.lovelytrip.entity.TripEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TripMapper{

    public TripEntity mapToEntity(TripCreateRequest tripCreateRequest) {

        TripEntity tripEntity = new TripEntity();

        tripEntity.setTitle(tripCreateRequest.getTitle());
        tripEntity.setDescription(tripCreateRequest.getDescription());
        tripEntity.setMainLocationId(tripCreateRequest.getMainLocationId());
        tripEntity.setMinDuration(tripCreateRequest.getMinDuration());
        tripEntity.setSupplierId(tripCreateRequest.getSupplierId());

        return tripEntity;
    }

    public TripCreateResponse mapToCreateResponse(TripEntity tripEntity) {

        TripCreateResponse tripCreateResponse = new TripCreateResponse();

        if (tripEntity == null || tripEntity.getId() == null) {
            return tripCreateResponse;
        }
        tripCreateResponse.setId(tripEntity.getId());
        tripCreateResponse.setCreatedTime(tripEntity.getCreatedTime());

        return tripCreateResponse;
    }


    public TripDetailResponse mapToTripDetailResponse(TripEntity tripEntity, List<ImageDetail> imageDetailList, List<TourGroupDetail> tourGroupDetailList) {

        TripDetailResponse tripDetailResponse = new TripDetailResponse();

        if (tripEntity == null || tripEntity.getId() == null) {
            return tripDetailResponse;
        }

        tripDetailResponse.setId(tripEntity.getId());
        tripDetailResponse.setTitle(tripEntity.getTitle());
        tripDetailResponse.setDescription(tripEntity.getDescription());
        tripDetailResponse.setMainLocationId(tripEntity.getMainLocationId());
        tripDetailResponse.setMinDuration(tripEntity.getMinDuration());

        tripDetailResponse.setImageList(imageDetailList != null ? imageDetailList : new ArrayList<>());
        tripDetailResponse.setTourGroupList(tourGroupDetailList != null ? tourGroupDetailList : new ArrayList<>());

        return tripDetailResponse;
    }
}
