package com.vic.lovelytrip.mapper;

import com.vic.lovelytrip.dto.BaseDto;
import com.vic.lovelytrip.dto.BookingDto;
import com.vic.lovelytrip.entity.BaseEntity;
import com.vic.lovelytrip.entity.BookingEntity;
import com.vic.lovelytrip.lib.MessageInfoContainer;

public class BookingMapper extends BaseMapper {

    @Override
    BaseEntity mapToEntity(BaseDto baseDto) {

        BookingDto bookingDto = (BookingDto) baseDto;

        BookingEntity bookingEntity = new BookingEntity();

        bookingEntity.setUserId(bookingDto.getUserId());
        bookingEntity.setTourGroupId(bookingDto.getTourGroupId());
        bookingEntity.setParticipantCount(bookingDto.getParticipantCount());
        bookingEntity.setUnitPrice(bookingDto.getUnitPrice());
        bookingEntity.setTotalAmount(bookingDto.getTotalAmount());
        bookingEntity.setFinalAmount(bookingDto.getFinalAmount());
        bookingEntity.setDiscountType(bookingDto.getDiscountType());
        bookingEntity.setDiscountAmount(bookingDto.getDiscountAmount());
        bookingEntity.setStatus(bookingDto.getStatus());

        return bookingEntity;
    }

    @Override
    BaseDto mapToDto(BaseEntity baseEntity, MessageInfoContainer messageInfoContainer) {

        BookingEntity bookingEntity = (BookingEntity) baseEntity;
        BookingDto bookingDto = new BookingDto();

        bookingDto.setUserId(bookingEntity.getUserId());
        bookingDto.setTourGroupId(bookingEntity.getTourGroupId());
        bookingDto.setParticipantCount(bookingEntity.getParticipantCount());
        bookingDto.setUnitPrice(bookingEntity.getUnitPrice());
        bookingDto.setTotalAmount(bookingEntity.getTotalAmount());
        bookingDto.setFinalAmount(bookingEntity.getFinalAmount());
        bookingDto.setDiscountType(bookingEntity.getDiscountType());
        bookingDto.setDiscountAmount(bookingEntity.getDiscountAmount());
        bookingDto.setStatus(bookingEntity.getStatus());

        bookingDto.setId(bookingEntity.getId());
        bookingDto.setCreatedTime(bookingEntity.getCreatedTime());
        bookingDto.setUpdatedTime(bookingEntity.getUpdatedTime());
        bookingDto.setMessageInfoContainer(messageInfoContainer);

        return bookingDto;
    }
}
