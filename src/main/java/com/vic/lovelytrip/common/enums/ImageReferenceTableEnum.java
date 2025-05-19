package com.vic.lovelytrip.common.enums;

public enum ImageReferenceTableEnum {

    USER(0),
    TRIP(1),
    TOUR_GROUP(2);

    private int code;

    ImageReferenceTableEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
