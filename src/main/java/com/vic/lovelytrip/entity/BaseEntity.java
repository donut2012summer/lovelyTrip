package com.vic.lovelytrip.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@MappedSuperclass
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    // Let PostgreSQL handle default current_timestamp
    @Column (updatable = false, insertable = false)
    private LocalDateTime createAt;

}
