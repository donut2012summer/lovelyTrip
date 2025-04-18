package com.vic.lovelytrip.controller;

import com.vic.lovelytrip.dto.BaseDto;
import com.vic.lovelytrip.dto.TripDto;
import com.vic.lovelytrip.dto.restservice.RestServiceRequest;
import com.vic.lovelytrip.dto.restservice.RestServiceResponse;
import com.vic.lovelytrip.mapper.TripMapper;
import com.vic.lovelytrip.service.TripService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trips")
public class TripController {

    private TripService tripService;

    private TripMapper tripMapper;

    @Autowired
    TripController(TripService tripService, TripMapper tripMapper) {
        this.tripService = tripService;
        this.tripMapper = tripMapper;
    }

    @GetMapping("{tripId}")
    RestServiceResponse<BaseDto> getTripByTripId(
              @PathVariable long tripId
            , @RequestParam(name="includeTourGroups", defaultValue = "false") boolean includeTourGroups) {

        return toResponse(tripService.getTripById(tripId, includeTourGroups));
    }


    @PostMapping
    RestServiceResponse<BaseDto> saveTrip(@RequestBody RestServiceRequest<TripDto> restServiceRequest) {
        return toResponse(tripService.saveTrip(tripMapper.mapToEntity(restServiceRequest.getBody())));
    }

    /**
     * Wraps a DTO in a {@link RestServiceResponse}.
     *
     * @param baseDto the base DTO to be wrapped
     * @return a {@link RestServiceResponse} containing the given base DTO
     * @implNote This is a utility method for standardizing API response format.
     * */
    private RestServiceResponse<BaseDto> toResponse(BaseDto baseDto) {

        RestServiceResponse<BaseDto> response = new RestServiceResponse<>();
        response.setResponseBody(baseDto);

        return response;

    }


}
