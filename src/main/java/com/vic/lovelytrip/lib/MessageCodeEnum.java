package com.vic.lovelytrip.lib;


import lombok.Getter;

@Getter
public enum MessageCodeEnum {

    DATA_NOT_FOUND("E0001", "{0} not found! "),
    REQUIRE_NOT_BLANK("E0002", "{0} , should not be blank! "),
    INVALID_FORMAT("E0003", "{0} , invalid field format! "),
    REQUIRE_AT_LEAST_ONE("E0004", "{0} , require at least one! ");

    private String messageCode;

    private String message;

    MessageCodeEnum(String messageCode, String message) {
        this.messageCode = messageCode;
        this.message = message;
    }


}
