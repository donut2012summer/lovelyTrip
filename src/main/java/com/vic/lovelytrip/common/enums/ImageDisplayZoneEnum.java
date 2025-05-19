package com.vic.lovelytrip.common.enums;

public enum ImageDisplayZoneEnum {

    DISPLAY_ZONE_BANNER(0),
    DISPLAY_ZONE_BANNER_GALLERY(1),
    DISPLAY_ZONE_BANNER_CONTENT(2);

    private int code;

    ImageDisplayZoneEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
