package com.vic.lovelytrip.repository;

import com.vic.lovelytrip.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
}
