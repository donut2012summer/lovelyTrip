package com.vic.lovelytrip.repository;

import com.vic.lovelytrip.entity.TourGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourGroupRepository extends JpaRepository<TourGroupEntity, Long> {

    @Query(value = "SELECT * FROM lovely_trip.tour_group WHERE trip_id = :tripId AND status = 1"
            , nativeQuery = true)
    List<TourGroupEntity> getAvailableTourGroupListByTripId(Long tripId);

}
