package com.vic.lovelytrip.lib;


import lombok.Getter;

@Getter
public enum MessageCodeEnum {

    E0001("E0001", "{0} , data not found! ");

    private String messageCode;

    private String message;

    MessageCodeEnum(String messageCode, String message) {
        this.messageCode = messageCode;
        this.message = message;
    }


}
