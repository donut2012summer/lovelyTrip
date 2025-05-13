package com.vic.lovelytrip.lib;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException {

    private final HttpStatusEnum httpStatusEnum;

    private final MessageInfoContainer messageInfoContainer;

    public BusinessException(HttpStatusEnum httpStatusEnum, MessageInfoContainer messageInfoContainer) {
        super("Business validation failed");
        this.httpStatusEnum = httpStatusEnum;
        this.messageInfoContainer = messageInfoContainer;
    }

}
