package com.vic.lovelytrip.entity;

import com.vic.lovelytrip.lib.MessageCodeEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "trip")
public class TripEntity extends BaseEntity {

    private String title;

    private String description;

    private String destination;

    private int duration;

}
