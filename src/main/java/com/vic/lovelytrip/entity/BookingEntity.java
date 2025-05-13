package com.vic.lovelytrip.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Table(name = "booking")
@Data
public class BookingEntity extends BaseEntity{
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
