package com.vic.lovelytrip.repository;

import com.vic.lovelytrip.entity.ImageEntity;
import com.vic.lovelytrip.common.annotation.Traceable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImageRepository extends CrudRepository<ImageEntity, Long>, ImageRepositoryCustom {


    @Traceable
    List<ImageEntity> findByReferenceIdAndReferenceTable(long referenceId, int referenceTableId);

}
