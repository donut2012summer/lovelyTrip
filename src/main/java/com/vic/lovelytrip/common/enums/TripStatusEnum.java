package com.vic.lovelytrip.common.enums;

public enum TripStatusEnum {

    DELETED(0),
    PUBLISHED(1);
    
    int code;

    TripStatusEnum(int code) {
        this.code = code;
    }

    public int getStatusCode() {
        return code;
    }
}
