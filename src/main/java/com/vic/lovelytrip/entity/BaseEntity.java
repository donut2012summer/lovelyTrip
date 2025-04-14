package com.vic.lovelytrip.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;

@MappedSuperclass
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // for unit test
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include
    private Long id;

    @CreatedDate // jpa generated the time when the obj is persisted
    @Column (insertable = false, updatable = false)
    private OffsetDateTime createdTime;

    @LastModifiedDate
    @Column (insertable = false, updatable = false)
    private OffsetDateTime updatedTime;

}
