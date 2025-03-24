package com.vic.lovelytrip.mapper;

import com.vic.lovelytrip.dto.BaseDto;
import com.vic.lovelytrip.dto.TripDto;
import com.vic.lovelytrip.entity.BaseEntity;
import com.vic.lovelytrip.entity.TripEntity;
import com.vic.lovelytrip.lib.MessageContainer;

public class TripMapper extends BaseMapper {

    @Override
    public BaseEntity mapToEntity(BaseDto baseDto) {

        TripDto tripDto = (TripDto) baseDto;

        TripEntity tripEntity = new TripEntity();
        tripEntity.setId(tripDto.getId());
        tripEntity.setDescription(tripDto.getDescription());
        tripEntity.setDuration(tripDto.getDuration());
        tripEntity.setTitle(tripDto.getTitle());
        tripEntity.setDestination(tripDto.getDestination());


        return tripEntity;
    }

    @Override
    public BaseDto mapToDto(BaseEntity baseEntity, MessageContainer messageContainer) {

        TripEntity tripEntity = (TripEntity) baseEntity;

        TripDto tripDto = new TripDto();
        tripDto.setId(tripEntity.getId());
        tripDto.setDescription(tripEntity.getDescription());
        tripDto.getDestination();
        tripDto.setDuration(tripEntity.getDuration());
        tripDto.setMessageContainer(messageContainer);

        return tripDto;
    }
}
