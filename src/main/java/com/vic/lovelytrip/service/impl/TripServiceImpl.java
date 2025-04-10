package com.vic.lovelytrip.service.impl;

import com.vic.lovelytrip.dto.BaseDto;
import com.vic.lovelytrip.entity.BaseEntity;
import com.vic.lovelytrip.entity.TripEntity;
import com.vic.lovelytrip.lib.MessageInfoContainer;
import com.vic.lovelytrip.mapper.TripMapper;
import com.vic.lovelytrip.repository.SupplierRepository;
import com.vic.lovelytrip.repository.TripRepository;
import com.vic.lovelytrip.service.TripService;
import com.vic.lovelytrip.validator.TripValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripServiceImpl implements TripService {

    private TripRepository tripRepository;

    private TripValidator tripValidator;

    private TripMapper tripMapper;

    @Autowired
    TripServiceImpl (TripRepository tripRepository, TripValidator tripValidator, TripMapper tripMapper) {
        this.tripRepository = tripRepository;
        this.tripValidator = tripValidator;
        this.tripMapper = tripMapper;
    }


    @Override
    public BaseDto saveTrip(BaseEntity baseEntity) {

        TripEntity tripEntity = (TripEntity) baseEntity;

        // check required fields
        MessageInfoContainer messageInfoContainer = tripValidator.validate(tripEntity);

        // if messageContainer.isEmpty : saveTrip
        if (messageInfoContainer.size() == 0){
            tripEntity = tripRepository.save(tripEntity);
        }

        // map to dto
        return tripMapper.mapToDto(tripEntity, messageInfoContainer);

    }


}
