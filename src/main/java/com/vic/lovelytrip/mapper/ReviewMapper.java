package com.vic.lovelytrip.mapper;

import com.vic.lovelytrip.dto.BaseDto;
import com.vic.lovelytrip.dto.ReviewDto;
import com.vic.lovelytrip.entity.BaseEntity;
import com.vic.lovelytrip.entity.ReviewEntity;
import com.vic.lovelytrip.lib.MessageInfoContainer;

public class ReviewMapper extends BaseMapper{
    @Override
    BaseEntity mapToEntity(BaseDto baseDto) {

        ReviewDto reviewDto = (ReviewDto) baseDto;
        ReviewEntity reviewEntity = new ReviewEntity();

        reviewEntity.setTripId(reviewDto.getTripId());
        reviewEntity.setUserId(reviewDto.getUserId());
        reviewEntity.setRating(reviewDto.getRating());
        reviewEntity.setComment(reviewDto.getComment());

        return reviewEntity;
    }

    @Override
    BaseDto mapToDto(BaseEntity baseEntity, MessageInfoContainer messageInfoContainer) {

        ReviewEntity reviewEntity = (ReviewEntity) baseEntity;
        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setTripId(reviewEntity.getTripId());
        reviewDto.setUserId(reviewEntity.getUserId());
        reviewDto.setRating(reviewEntity.getRating());
        reviewDto.setComment(reviewEntity.getComment());

        reviewDto.setId(reviewEntity.getId());
        reviewDto.setCreatedTime(reviewEntity.getCreatedTime());
        reviewDto.setUpdatedTime(reviewEntity.getUpdatedTime());
        reviewDto.setMessageInfoContainer(messageInfoContainer);
        return reviewDto;
    }
}
