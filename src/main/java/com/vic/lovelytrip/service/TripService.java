package com.vic.lovelytrip.service;

import com.vic.lovelytrip.dto.BaseDto;
import com.vic.lovelytrip.entity.BaseEntity;

public interface TripService {

    BaseDto saveTrip(BaseEntity trip);

    BaseDto getTripById(Long id, boolean includeTourGroups);

}
