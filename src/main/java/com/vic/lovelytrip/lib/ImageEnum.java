package com.vic.lovelytrip.lib;

public enum ImageEnum {

    USER(0),

    TRIP(1),

    TOUR_GROUP(2),

    BANNER(0),
    GALLERY(1),
    CONTENT(2);


    private int code;

    ImageEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
