package com.vic.lovelytrip.mapper;

import com.vic.lovelytrip.dto.BaseDto;
import com.vic.lovelytrip.dto.TripDto;
import com.vic.lovelytrip.entity.BaseEntity;
import com.vic.lovelytrip.entity.TourGroupEntity;
import com.vic.lovelytrip.entity.TripEntity;
import com.vic.lovelytrip.lib.MessageInfoContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TripMapper extends BaseMapper {

    private TourGroupMapper tourGroupMapper;

    @Autowired
    public TripMapper(TourGroupMapper tourGroupMapper) {
        this.tourGroupMapper = tourGroupMapper;
    }

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

        TripDto tripDto = new TripDto();
        tripDto.setMessageInfoContainer(messageInfoContainer);

        if ( null == baseEntity ) {
            return tripDto;
        }

        TripEntity tripEntity = (TripEntity) baseEntity;

        // TODO check not null area (in this area, the content should all not be null)
        if( null != tripEntity.getId()){

            tripDto.setTitle(tripEntity.getTitle());
            tripDto.setDescription(tripEntity.getDescription());
            tripDto.setDestination(tripEntity.getDestination());
            tripDto.setDuration(tripEntity.getDuration());
            tripDto.setSupplierId(tripEntity.getSupplierId());

            tripDto.setId(tripEntity.getId());
            tripDto.setCreatedTime(tripEntity.getCreatedTime());
            tripDto.setUpdatedTime(tripEntity.getUpdatedTime());

        }

        List<TourGroupEntity> tourGroupEntityList = tripEntity.getTourGroupEntityList();

        if ( null != tourGroupEntityList && !tourGroupEntityList.isEmpty()) {
            tripDto.setTourGroupDtoList(tourGroupMapper.batchMapToDto(tourGroupEntityList));
        }

        return tripDto;
    }
}
