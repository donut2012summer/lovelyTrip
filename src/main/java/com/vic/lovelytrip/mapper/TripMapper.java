package com.vic.lovelytrip.mapper;

import com.vic.lovelytrip.dto.BaseDto;
import com.vic.lovelytrip.dto.TripDto;
import com.vic.lovelytrip.entity.BaseEntity;
import com.vic.lovelytrip.entity.TripEntity;
import com.vic.lovelytrip.lib.MessageInfoContainer;
import org.springframework.stereotype.Component;

@Component
public class TripMapper extends BaseMapper {

    @Override
    public BaseEntity mapToEntity(BaseDto baseDto) {

        TripDto tripDto = (TripDto) baseDto;

        TripEntity tripEntity = new TripEntity();

        tripEntity.setTitle(tripDto.getTitle());
        tripEntity.setDescription(tripDto.getDescription());
        tripEntity.setDestination(tripDto.getDestination());
        tripEntity.setDuration(tripDto.getDuration());
        tripEntity.setSupplierId(tripDto.getSupplierId());

        return tripEntity;
    }

    @Override
    public BaseDto mapToDto(BaseEntity baseEntity, MessageInfoContainer messageInfoContainer) {

        TripEntity tripEntity = (TripEntity) baseEntity;

        TripDto tripDto = new TripDto();

        tripDto.setTitle(tripEntity.getTitle());
        tripDto.setDescription(tripEntity.getDescription());
        tripDto.setDestination(tripEntity.getDestination());
        tripDto.setDuration(tripEntity.getDuration());
        tripDto.setSupplierId(tripEntity.getSupplierId());

        tripDto.setCreatedTime(tripEntity.getCreatedTime());
        tripDto.setUpdatedTime(tripEntity.getUpdatedTime());
        tripDto.setMessageInfoContainer(messageInfoContainer);

        return tripDto;
    }
}
