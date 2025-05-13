package com.vic.lovelytrip.service;

import com.vic.lovelytrip.dto.*;

import java.util.List;

public interface TripService {

    /**
     * Creates a new trip based on the provided trip creation request.
     *
     * @param tripCreateRequest the request object containing trip details such as title, description, location, duration, and associated images.
     * @return a {@link TripCreateResponse} object containing the generated trip ID and creation metadata.
     * @throws com.vic.lovelytrip.lib.BusinessException if the input request is invalid or contains errors.
     */
    TripCreateResponse createTrip(TripCreateRequest tripCreateRequest);

    /**
     * Retrieves detailed information of a trip by its ID.
     *
     * @param id the unique identifier of the trip to retrieve.
     * @param includeTourGroups whether to include associated tour group details in the response.
     * @return a {@link TripDetailResponse} containing trip details and optionally its tour groups.
     * @throws NotFoundException if no trip exists with the given ID.
     */
    TripDetailResponse getTripDetailById(Long id, boolean includeTourGroups);

    /**
     * Searches for trips based on the given search criteria.
     *
     * @param tripSearchRequest the request containing filters such as keyword, location, duration, or other parameters.
     * @return a list of {@link TripSearchResponse} objects matching the search criteria; may be empty if no matches are found.
     */
    List<TripSearchResponse> searchTrip(TripSearchRequest tripSearchRequest);

}
