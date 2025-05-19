package com.vic.lovelytrip.repository;


import com.vic.lovelytrip.entity.TripEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface TripRepository extends CrudRepository<TripEntity, Long>, TripRepositoryCustom {

}
