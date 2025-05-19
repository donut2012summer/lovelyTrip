package com.vic.lovelytrip.common.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageInfo {

    private String messageCode;
    private String message;

    MessageInfo(String messageCode, String message) {
        this.messageCode = messageCode;
        this.message = message;
    }


}
