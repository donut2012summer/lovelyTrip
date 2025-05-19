package com.vic.lovelytrip.mapper;

import com.vic.lovelytrip.dto.TourGroupDetail;
import com.vic.lovelytrip.entity.TourGroupEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TourGroupMapper {

    public TourGroupDetail mapToTourGroupDetail(TourGroupEntity tourGroupEntity){
        TourGroupDetail tourGroupDetail = new TourGroupDetail();

        tourGroupDetail.setId(tourGroupEntity.getId());
        tourGroupDetail.setTripId(tourGroupEntity.getTripId());
        tourGroupDetail.setTitle(tourGroupEntity.getTitle());
        tourGroupDetail.setDescription(tourGroupEntity.getDescription());
        tourGroupDetail.setReturnDate(tourGroupEntity.getReturnDate());
        tourGroupDetail.setDepartureDate(tourGroupEntity.getDepartureDate());
        tourGroupDetail.setUnitPrice(tourGroupEntity.getUnitPrice());
        tourGroupDetail.setAvailability(tourGroupEntity.getAvailability());

        return tourGroupDetail;
    }

    public List<TourGroupDetail> batchMapToTourGroupDetail(List<TourGroupEntity> tourGroupEntityList){

        List<TourGroupDetail> tourGroupDetailList = new ArrayList<>();

        if (tourGroupEntityList == null || tourGroupEntityList.isEmpty()){
            return tourGroupDetailList;
        }

        for(TourGroupEntity tourGroupEntity : tourGroupEntityList){
            TourGroupDetail tourGroupDetail = mapToTourGroupDetail(tourGroupEntity);
            tourGroupDetailList.add(tourGroupDetail);
        }
        return tourGroupDetailList;
    }
}
