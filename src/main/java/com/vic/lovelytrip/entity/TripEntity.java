package com.vic.lovelytrip.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "trip")
public class TripEntity extends BaseEntity {

    private String title;

    private String description;

    private String destination;

    private int duration;

}
