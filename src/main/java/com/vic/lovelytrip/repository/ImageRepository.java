package com.vic.lovelytrip.repository;

import com.vic.lovelytrip.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
}
