package com.vic.lovelytrip.repository;

import com.vic.lovelytrip.entity.SupplierEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierCrudRepository extends CrudRepository<SupplierEntity, Long> {
}
