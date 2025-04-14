package com.vic.lovelytrip.mapper;

import com.vic.lovelytrip.dto.BaseDto;
import com.vic.lovelytrip.dto.TourGroupDto;
import com.vic.lovelytrip.entity.BaseEntity;
import com.vic.lovelytrip.entity.TourGroupEntity;
import com.vic.lovelytrip.lib.MessageInfoContainer;

public class TourGroupMapper extends BaseMapper{
    @Override
    BaseEntity mapToEntity(BaseDto baseDto) {

        TourGroupDto tourGroupDto = (TourGroupDto) baseDto;
        TourGroupEntity tourGroupEntity = new TourGroupEntity();

        tourGroupEntity.setTripId(tourGroupDto.getTripId());
        tourGroupEntity.setTitle(tourGroupDto.getTitle());
        tourGroupEntity.setDescription(tourGroupDto.getDescription());
        tourGroupEntity.setDepartureDate(tourGroupDto.getDepartureDate());
        tourGroupEntity.setReturnDate(tourGroupDto.getReturnDate());
        tourGroupEntity.setUnitPrice(tourGroupDto.getUnitPrice());
        tourGroupEntity.setAvailability(tourGroupDto.getAvailability());
        tourGroupEntity.setStatus(tourGroupDto.getStatus());

        return tourGroupEntity;
    }

    @Override
    BaseDto mapToDto(BaseEntity baseEntity, MessageInfoContainer messageInfoContainer) {

        TourGroupEntity tourGroupEntity = (TourGroupEntity) baseEntity;
        TourGroupDto tourGroupDto = new TourGroupDto();

        tourGroupDto.setTripId(tourGroupEntity.getTripId());
        tourGroupDto.setTitle(tourGroupEntity.getTitle());
        tourGroupDto.setDescription(tourGroupEntity.getDescription());
        tourGroupDto.setDepartureDate(tourGroupEntity.getDepartureDate());
        tourGroupDto.setReturnDate(tourGroupEntity.getReturnDate());
        tourGroupDto.setUnitPrice(tourGroupEntity.getUnitPrice());
        tourGroupDto.setAvailability(tourGroupEntity.getAvailability());
        tourGroupDto.setStatus(tourGroupEntity.getStatus());

        tourGroupDto.setId(tourGroupEntity.getId());
        tourGroupDto.setCreatedTime(tourGroupEntity.getCreatedTime());
        tourGroupDto.setUpdatedTime(tourGroupEntity.getUpdatedTime());
        tourGroupDto.setMessageInfoContainer(messageInfoContainer);

        return tourGroupDto;
    }
}
