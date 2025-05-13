package com.vic.lovelytrip.repository;

import com.vic.lovelytrip.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationCrudRepository extends JpaRepository<LocationEntity, Long> {
}
