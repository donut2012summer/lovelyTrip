package com.vic.lovelytrip.service.impl;

import com.vic.lovelytrip.dto.BaseDto;
import com.vic.lovelytrip.dto.TourGroupDto;
import com.vic.lovelytrip.dto.TripDto;
import com.vic.lovelytrip.entity.BaseEntity;
import com.vic.lovelytrip.entity.TourGroupEntity;
import com.vic.lovelytrip.entity.TripEntity;
import com.vic.lovelytrip.lib.MessageCodeEnum;
import com.vic.lovelytrip.lib.MessageInfoContainer;
import com.vic.lovelytrip.mapper.TourGroupMapper;
import com.vic.lovelytrip.mapper.TripMapper;
import com.vic.lovelytrip.repository.SupplierRepository;
import com.vic.lovelytrip.repository.TourGroupRepository;
import com.vic.lovelytrip.repository.TripRepository;
import com.vic.lovelytrip.service.TripService;
import com.vic.lovelytrip.validator.TripValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TripServiceImpl implements TripService {
    private TourGroupRepository tourGroupRepository;

    private TripRepository tripRepository;

    private TripValidator tripValidator;

    private TripMapper tripMapper;

    private TourGroupMapper tourGroupMapper = new TourGroupMapper();

    @Autowired
    public TripServiceImpl (TripRepository tripRepository, TripValidator tripValidator, TripMapper tripMapper, TourGroupRepository tourGroupRepository) {
        this.tripRepository = tripRepository;
        this.tripValidator = tripValidator;
        this.tripMapper = tripMapper;
        this.tourGroupRepository = tourGroupRepository;
    }


    @Override
    public BaseDto saveTrip(BaseEntity baseEntity) {

        TripEntity tripEntity = (TripEntity) baseEntity;

        MessageInfoContainer messageInfoContainer = tripValidator.validate(tripEntity);

        if (messageInfoContainer.size() == 0){
            tripEntity = tripRepository.save(tripEntity);
        }

        return tripMapper.mapToDto(tripEntity, messageInfoContainer);
    }

    @Override
    public BaseDto getTripById(Long id, boolean includeTourGroups) {

        MessageInfoContainer messageInfoContainer = new MessageInfoContainer();

        TripEntity tripEntity = findValidTrip(id, messageInfoContainer);

        if ( null != tripEntity.getId() && includeTourGroups){
            tripEntity.setTourGroupEntityList(tourGroupRepository.getAvailableTourGroupListByTripId(id));
        }

        return tripMapper.mapToDto(tripEntity, messageInfoContainer);

    }

    /**
     * Validate trip id not blank, and trip id exist
     *
     * @param
     * @return
     * @remark
     * */
    private TripEntity findValidTrip(long tripId, MessageInfoContainer messageInfoContainer) {

        TripEntity tripEntity = new TripEntity();

        boolean isBlank = !tripValidator.validateNotBlank("trip.id", tripId, messageInfoContainer);

        if (isBlank){
            return tripEntity;
        }

        Optional<TripEntity> tripEntityOptional = tripRepository.findById(tripId);


        if (tripEntityOptional.isPresent()){
            tripEntity = tripEntityOptional.get();

        }else {
            messageInfoContainer.addMessage(MessageCodeEnum.DATA_NOT_FOUND, "Trip");
        }

        return tripEntity;
    }

}
