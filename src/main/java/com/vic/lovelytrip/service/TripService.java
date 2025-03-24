package com.vic.lovelytrip.service;

import com.vic.lovelytrip.dto.BaseDto;
import com.vic.lovelytrip.entity.BaseEntity;
import com.vic.lovelytrip.entity.TripEntity;
import org.springframework.stereotype.Service;

@Service
public interface TripService {


    BaseDto saveTrip(BaseEntity trip);


}
