package com.vic.lovelytrip.mapper;

import com.vic.lovelytrip.dto.BaseDto;
import com.vic.lovelytrip.entity.BaseEntity;

public abstract class BaseMapper {

    // toEntity
    abstract BaseEntity convertToEntity(BaseDto baseDto);

    // to dto
    abstract BaseDto convertToDto(BaseEntity baseEntity);

}
