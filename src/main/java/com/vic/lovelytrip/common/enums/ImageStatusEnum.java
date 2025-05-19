package com.vic.lovelytrip.common.enums;

public enum ImageStatusEnum {

    TEMPORARY(0),
    STORED(1),
    DELETED(2);

    private int code;
    ImageStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
