package com.vic.lovelytrip;

import com.vic.lovelytrip.dto.ImageCreateRequest;
import com.vic.lovelytrip.dto.TripCreateRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestDataFactory {

    public static TripCreateRequest getValidTripCreateRequest() {
        TripCreateRequest tripCreateRequest = new TripCreateRequest();
        tripCreateRequest.setTitle("Day 1 trip to tokyo");
        tripCreateRequest.setDescription("Includes meals guide entrance !!!");

        // supplier id existed in DB
        tripCreateRequest.setSupplierId(1);

        // location id existed in DB
        tripCreateRequest.setMainLocationId(3);
        tripCreateRequest.setMinDuration(1);

        List<ImageCreateRequest> images = Arrays.asList(new ImageCreateRequest(), new ImageCreateRequest());
        tripCreateRequest.setImageCreateRequestList(images);

        return tripCreateRequest;
    }

    public static TripCreateRequest getInvalidFormatTripCreateRequest() {
        TripCreateRequest tripCreateRequest = new TripCreateRequest();

        // title is wrong format : INVALID_FORMAT
        tripCreateRequest.setTitle("Day 1 trip to tokyo !@!");

        // description is empty : REQUIRE_NOT_BLANK
        tripCreateRequest.setDescription(" ");

        // location id invalid format : INVALID_FORMAT
        tripCreateRequest.setMainLocationId(0);

        // min duration is negative : INVALID_FORMAT
        tripCreateRequest.setMinDuration(-1);

        // blank image list : REQUIRE_AT_LEAST_ONE
        List<ImageCreateRequest> images = new ArrayList<>();
        tripCreateRequest.setImageCreateRequestList(images);

        return tripCreateRequest;
    }
}
