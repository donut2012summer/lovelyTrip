package com.vic.lovelytrip.repository;

import com.vic.lovelytrip.entity.ImageEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

// because need autowire sql loader, use integration test here
@SpringBootTest
@Transactional
public class ImageRepositoryTest {

    @Qualifier("imageRepository")
    @Autowired
    private ImageRepository repository;


    @Test
    void testFindByReferenceIdAndReferenceTable() {
        List<ImageEntity> imageEntityList = repository.findByReferenceIdAndReferenceTable(1, 1);
        int row = repository.updateStatusById(1, 1L);
        assertThat(imageEntityList).hasSize(2);
        assertEquals(1, row);
    }


}
