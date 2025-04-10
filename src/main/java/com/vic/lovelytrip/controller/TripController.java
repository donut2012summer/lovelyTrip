package com.vic.lovelytrip.controller;

import com.vic.lovelytrip.dto.BaseDto;
import com.vic.lovelytrip.dto.TripDto;
import com.vic.lovelytrip.dto.restservice.RestServiceRequest;
import com.vic.lovelytrip.dto.restservice.RestServiceResponse;
import com.vic.lovelytrip.mapper.TripMapper;
import com.vic.lovelytrip.service.TripService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TripController {

    private TripService tripService;
    private TripMapper tripMapper;

    @Autowired
    TripController(TripService tripService) {
        this.tripService = tripService;
        this.tripMapper = new TripMapper();
    }

    @PostMapping("/fetch")
    RestServiceResponse<BaseDto> saveTrip(@RequestBody RestServiceRequest<TripDto> restServiceRequest) {
        return toResponse(tripService.saveTrip(tripMapper.mapToEntity(restServiceRequest.getBody())));
    }

    private RestServiceResponse<BaseDto> toResponse(BaseDto baseDto) {

        RestServiceResponse<BaseDto> response = new RestServiceResponse<>();
        response.setResponseBody(baseDto);

        return response;

    }


}
