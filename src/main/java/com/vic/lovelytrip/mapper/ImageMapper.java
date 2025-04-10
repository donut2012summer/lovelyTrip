package com.vic.lovelytrip.mapper;


import com.vic.lovelytrip.dto.BaseDto;
import com.vic.lovelytrip.dto.ImageDto;
import com.vic.lovelytrip.entity.BaseEntity;
import com.vic.lovelytrip.entity.ImageEntity;
import com.vic.lovelytrip.lib.MessageInfoContainer;

public class ImageMapper extends BaseMapper {
    @Override
    BaseEntity mapToEntity(BaseDto baseDto) {

        // convert baseDto to ImageDto
        ImageDto imageDto = (ImageDto) baseDto;
        // new a imageEntity

        ImageEntity imageEntity = new ImageEntity();
        // set data
        imageEntity.setReference_table(imageDto.getReference_table());
        imageEntity.setReference_id(imageDto.getReference_id());
        imageEntity.setImageUrl(imageDto.getImageUrl());
        imageEntity.setImageZone(imageDto.getImageZone());
        imageEntity.setDisplayOrder(imageDto.getDisplayOrder());

        return imageEntity;
    }

    @Override
    BaseDto mapToDto(BaseEntity baseEntity, MessageInfoContainer messageInfoContainer) {

        // convert baseEntity to imageEntity
        ImageEntity imageEntity = (ImageEntity) baseEntity;

        // new a imageDto
        ImageDto imageDto = new ImageDto();

        // set data + id, timestamp and messageInfoContainer
        imageDto.setReference_table(imageEntity.getReference_table());
        imageDto.setReference_id(imageEntity.getReference_id());
        imageDto.setImageUrl(imageEntity.getImageUrl());
        imageDto.setImageZone(imageEntity.getImageZone());
        imageDto.setDisplayOrder(imageEntity.getDisplayOrder());

        imageDto.setId(imageEntity.getId());
        imageDto.setCreatedTime(imageEntity.getCreatedTime());
        imageDto.setUpdatedTime(imageEntity.getUpdatedTime());
        imageDto.setMessageInfoContainer(messageInfoContainer);

        return imageDto;
    }
}
