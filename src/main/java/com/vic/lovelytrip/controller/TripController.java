package com.vic.lovelytrip.controller;

import com.vic.lovelytrip.dto.*;
import com.vic.lovelytrip.dto.restservice.RestServiceRequest;
import com.vic.lovelytrip.dto.restservice.RestServiceResponse;
import com.vic.lovelytrip.common.enums.HttpStatusEnum;
import com.vic.lovelytrip.mapper.TripMapper;
import com.vic.lovelytrip.service.TripService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {

    private TripService tripService;

    @Autowired
    TripController(TripService tripService, TripMapper tripMapper) {
        this.tripService = tripService;
    }

    // @TODO
    @GetMapping
    RestServiceResponse<List<TripSearchResponse>> getTripSearch(@ModelAttribute TripSearchRequest tripSearchRequest) {
        return toResponse(tripService.searchTrip(tripSearchRequest));
    }


    /**
     * Retrieves the detailed information of a trip by its ID.
     *
     * @param tripId the unique identifier of the trip
     * @param includeTourGroups whether to include associated tour group data (optional, defaults to false)
     * @return a {@link RestServiceResponse} containing detailed trip information
     * @implNote This endpoint is typically used to fetch full trip content for display or editing.
     */
    @GetMapping("{tripId}")
    RestServiceResponse<TripDetailResponse> getTripByTripId(
              @PathVariable long tripId
            , @RequestParam(name="includeTourGroups", defaultValue = "false") boolean includeTourGroups) {

        return toResponse(tripService.getTripDetailById(tripId, includeTourGroups));
    }

    /**
     * Creates a new trip with the provided details.
     *
     * @param restServiceRequest a request wrapper containing the {@link TripCreateRequest} payload
     * @return a {@link RestServiceResponse} containing the result of the creation operation
     * @implNote This endpoint is used for registering a new trip, typically by admin or back-office systems.
     */
    @PostMapping
    RestServiceResponse<TripCreateResponse> createTrip(@RequestBody RestServiceRequest<TripCreateRequest> restServiceRequest) {
        return toResponse(tripService.createTrip(restServiceRequest.getBody()));
    }

    /**
     * Wraps a DTO in a {@link RestServiceResponse}.
     *
     * @param responseData the responseData to be wrapped
     * @return a {@link RestServiceResponse} containing the given base DTO
     * @implNote This is a utility method for standardizing API response format.
     * */
    private <T> RestServiceResponse<T> toResponse(T responseData) {

        RestServiceResponse<T> response = new RestServiceResponse<>();
        response.setResponseCode(HttpStatusEnum.OK.getStatusCode());
        response.setResponseDescription(HttpStatusEnum.OK.getStatusMessage());

        response.setResponseBody(responseData);

        return response;

    }


}
