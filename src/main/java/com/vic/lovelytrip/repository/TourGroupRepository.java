package com.vic.lovelytrip.repository;

import com.vic.lovelytrip.entity.TourGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourGroupRepository extends JpaRepository<TourGroupEntity, Long> {

}
