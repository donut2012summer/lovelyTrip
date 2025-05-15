package com.vic.lovelytrip.aspect;

import com.vic.lovelytrip.repository.ImageCrudRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TraceableAspectTest {

    @Autowired
    ImageCrudRepository imageCrudRepository;

    @Test
    void testAspectOnRepository(){
        imageCrudRepository.findByReferenceIdAndReferenceTable(1l, 1);
    }
}
