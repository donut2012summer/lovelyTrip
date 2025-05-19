package com.vic.lovelytrip.common.exception;

import com.vic.lovelytrip.common.enums.HttpStatusEnum;
import com.vic.lovelytrip.common.message.MessageInfoContainer;
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
