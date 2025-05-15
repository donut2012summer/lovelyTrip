package com.vic.lovelytrip.repository;

import com.vic.lovelytrip.entity.ImageEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@ActiveProfiles("test")
public class ImageCrudRepositoryTest {

    @Autowired
    private ImageCrudRepository repository;

    @Test
    void testFindByReferenceIdAndReferenceTable() {
        List<ImageEntity> imageEntityList = repository.findByReferenceIdAndReferenceTable(1, 1);
        assertThat(imageEntityList).hasSize(2);
    }


}
