package com.vic.lovelytrip.mapper;

import com.vic.lovelytrip.dto.BaseDto;
import com.vic.lovelytrip.dto.BookingDto;
import com.vic.lovelytrip.entity.BaseEntity;
import com.vic.lovelytrip.entity.BookingEntity;
import com.vic.lovelytrip.lib.MessageInfoContainer;

public class BookingMapper extends BaseMapper {

    @Override
    BaseEntity mapToEntity(BaseDto baseDto) {
        // convert baseDto to BookingDto
        BookingDto bookingDto = (BookingDto) baseDto;

        // new Booking Entity
        BookingEntity bookingEntity = new BookingEntity();

        // set data
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
        // convert baseEntity to bookingEntity
        BookingEntity bookingEntity = (BookingEntity) baseEntity;


        // new a bookingDto
        BookingDto bookingDto = new BookingDto();
        // set data + id + time stamp + messageInfoContainer

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
