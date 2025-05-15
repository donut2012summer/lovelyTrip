package com.vic.lovelytrip.repository;

import com.vic.lovelytrip.entity.LocationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationCrudRepository extends CrudRepository<LocationEntity, Long> {
}
