package com.vic.lovelytrip.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.OffsetDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true) // for unit test
@Data
public abstract class BaseEntity {

    @Id
    @EqualsAndHashCode.Include
    private Long id;

    @CreatedDate // jpa generated the time when the obj is persisted
    private OffsetDateTime createdTime;

    @LastModifiedDate
    private OffsetDateTime updatedTime;

}
