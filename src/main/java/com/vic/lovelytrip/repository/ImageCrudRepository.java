package com.vic.lovelytrip.repository;

import com.vic.lovelytrip.entity.ImageEntity;
import com.vic.lovelytrip.lib.Traceable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageCrudRepository extends CrudRepository<ImageEntity, Long> {

    @Traceable
    List<ImageEntity> findByReferenceIdAndReferenceTable(long referenceId, int referenceTableId);
}
