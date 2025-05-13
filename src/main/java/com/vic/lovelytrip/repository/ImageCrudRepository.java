package com.vic.lovelytrip.repository;

import com.vic.lovelytrip.entity.ImageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageCrudRepository extends CrudRepository<ImageEntity, Long> {

    List<ImageEntity> findByTripId(long tripId);
}
