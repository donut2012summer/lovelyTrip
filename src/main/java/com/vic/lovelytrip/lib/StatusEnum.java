package com.vic.lovelytrip.lib;

import lombok.Data;

public enum StatusEnum {

    TRIP_DELETED(0),
    TRIP_ACTIVE(1);
    
    int statusCode; 

    StatusEnum(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
