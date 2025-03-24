package com.vic.lovelytrip.controller;

import com.vic.lovelytrip.dto.BaseDto;
import com.vic.lovelytrip.dto.RestServiceRequest;
import com.vic.lovelytrip.dto.RestServiceResponse;
import com.vic.lovelytrip.mapper.TripMapper;
import com.vic.lovelytrip.service.TripService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/trip")
public class TripController {

    private TripService tripService;

    @Autowired
    TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping
    RestServiceResponse<BaseDto> saveTrip(@RequestBody RestServiceRequest<BaseDto> restServiceRequest) {
        return toResponse(tripService.saveTrip(new TripMapper().mapToEntity(restServiceRequest.getBody())));

    }

    private RestServiceResponse<BaseDto> toResponse(BaseDto baseDto) {

        RestServiceResponse<BaseDto> response = new RestServiceResponse<>();
        response.setResponseBody(baseDto);

        return response;

    }


}
