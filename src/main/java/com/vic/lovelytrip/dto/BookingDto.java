package com.vic.lovelytrip.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookingDto extends BaseDto{
    private long userId; // FK
    private long tourGroupId; // FK
    private long participantCount;
    private BigDecimal unitPrice;
    private BigDecimal totalAmount;
    private BigDecimal finalAmount;
    private String discountType;
    private BigDecimal discountAmount;
    private int status;
}
