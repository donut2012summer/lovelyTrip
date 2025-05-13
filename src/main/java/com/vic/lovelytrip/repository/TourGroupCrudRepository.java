package com.vic.lovelytrip.repository;

import com.vic.lovelytrip.entity.TourGroupEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourGroupCrudRepository extends CrudRepository<TourGroupEntity, Long> {

    List<TourGroupEntity> findByTripIdAndAvailabilityGreaterThan(Long tripId, Integer availability);

    List<TourGroupEntity> findByTripId(Long tripId);
}
