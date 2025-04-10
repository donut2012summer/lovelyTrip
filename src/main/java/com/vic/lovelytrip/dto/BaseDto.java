package com.vic.lovelytrip.dto;

import com.vic.lovelytrip.lib.MessageInfoContainer;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.OffsetDateTime;

@Data
public abstract class BaseDto {

    private long id;

    @CreatedDate
    private OffsetDateTime createdTime;

    @LastModifiedDate
    private OffsetDateTime updatedTime;

    private MessageInfoContainer messageInfoContainer;

}
