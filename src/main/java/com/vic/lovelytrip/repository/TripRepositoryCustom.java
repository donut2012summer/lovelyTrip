package com.vic.lovelytrip.repository;

import com.vic.lovelytrip.dto.TripSearchRequest;
import com.vic.lovelytrip.dto.TripSearchResponse;

import java.util.List;

public interface TripRepositoryCustom {
    public List<TripSearchResponse> searchTrips(TripSearchRequest request);
}
