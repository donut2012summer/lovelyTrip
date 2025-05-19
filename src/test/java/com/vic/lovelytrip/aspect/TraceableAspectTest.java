package com.vic.lovelytrip.aspect;

import com.vic.lovelytrip.repository.ImageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TraceableAspectTest {

    @Autowired
    ImageRepository imageRepository;

    @Test
    void testAspectOnRepository(){
        imageRepository.findByReferenceIdAndReferenceTable(1l, 1);
    }
}
