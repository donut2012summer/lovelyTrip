package com.vic.lovelytrip.dto;

import com.vic.lovelytrip.lib.MessageCodeEnum;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public abstract class BaseDto {

    private long id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private List<MessageCodeEnum> messageContainer;

    public static void main(String[] args) {

    }

}
