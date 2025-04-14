package com.vic.lovelytrip.mapper;

import com.vic.lovelytrip.dto.BaseDto;
import com.vic.lovelytrip.entity.BaseEntity;
import com.vic.lovelytrip.lib.MessageInfoContainer;

public abstract class BaseMapper {

    abstract BaseEntity mapToEntity(BaseDto baseDto);

    abstract BaseDto mapToDto(BaseEntity baseEntity, MessageInfoContainer messageInfoContainer);

}
