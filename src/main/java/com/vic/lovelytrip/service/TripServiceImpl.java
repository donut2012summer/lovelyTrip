package com.vic.lovelytrip.service;

import com.vic.lovelytrip.dto.BaseDto;
import com.vic.lovelytrip.entity.BaseEntity;
import com.vic.lovelytrip.entity.TripEntity;
import com.vic.lovelytrip.mapper.TripMapper;
import com.vic.lovelytrip.repository.TripRepository;
import com.vic.lovelytrip.validator.TripValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripServiceImpl implements TripService {

    TripRepository tripRepository;

    TripValidator tripValidator;

    @Autowired
    TripServiceImpl (TripRepository tripRepository, TripValidator tripValidator) {
        this.tripRepository = tripRepository;
        this.tripValidator = tripValidator;
    }

    @Override
    public BaseDto saveTrip(BaseEntity baseEntity) {

        TripEntity tripEntity = (TripEntity) baseEntity;

        // check required fields
        tripValidator.validate(tripEntity);

        // if messageContainer.isEmpty : saveTrip
        if (tripValidator.getMessageContainer().size() == 0){
            tripEntity = tripRepository.save(tripEntity);
        }

        // map to dto
        return new TripMapper().mapToDto(tripEntity, tripValidator.getMessageContainer());

    }


}
