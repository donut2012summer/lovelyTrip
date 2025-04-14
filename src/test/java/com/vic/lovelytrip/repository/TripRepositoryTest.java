package com.vic.lovelytrip.repository;

import com.vic.lovelytrip.entity.TripEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TripRepositoryTest {

    private final Logger logger = LoggerFactory.getLogger(TripRepositoryTest.class);
    private TripEntity tripEntity;

    @Autowired
    private TripRepository tripRepository;

    @BeforeEach
    void setUp(){
        logger.info("------ Set up TripEntity ------ ");
        tripEntity = new TripEntity();

        tripEntity.setTitle("Test Trip");
        tripEntity.setDescription("Test Description");
        tripEntity.setDestination("Test Destination");
        tripEntity.setDuration(10);
        tripEntity.setSupplierId(1);
    }

    @Test
    void testConnection(){

        logger.info("------ Start testConnection ------ ");

        List<TripEntity> tripEntityList = tripRepository.findAll();
        assert tripEntityList.isEmpty();

        logger.info("------ Connection test passed ------ ");
    }

    @Test
    void testSaveTripWithValidData(){

        logger.info("------ Start testSaveTripWithValidData ------ ");

        TripEntity returnedTripEntity = tripRepository.save(tripEntity);
        assert returnedTripEntity != null;
        assert returnedTripEntity.getTitle().equals("Test Trip");

        logger.info("------ testSaveTripWithValidData passed------ ");

    }

}
