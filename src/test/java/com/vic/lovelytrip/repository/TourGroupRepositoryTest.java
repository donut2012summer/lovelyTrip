package com.vic.lovelytrip.repository;

import com.vic.lovelytrip.entity.TourGroupEntity;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TourGroupRepositoryTest {

    private TourGroupRepository tourGroupRepository;

    private final Logger logger = LoggerFactory.getLogger(TourGroupRepositoryTest.class);

    @Autowired
    public TourGroupRepositoryTest(TourGroupRepository tourGroupRepository) {
        this.tourGroupRepository = tourGroupRepository;
    }

    @Test
    void testGetAvailableTourGroupListByTripId_withValidTripId(){

        List<TourGroupEntity> tourGroupEntityList = tourGroupRepository.getAvailableTourGroupListByTripId(2l);
        assertFalse(tourGroupEntityList.isEmpty());
        assertTrue(tourGroupEntityList.size() > 0);

        TourGroupEntity tourGroupEntity = tourGroupEntityList.get(0);
        assertEquals(2l, tourGroupEntity.getTripId());
        assertEquals(1, tourGroupEntity.getStatus());

    }

    @Test
    void testGetAvailableTourGroupListByTripId_withoutValidTripId(){
        List<TourGroupEntity> tourGroupEntityList = tourGroupRepository.getAvailableTourGroupListByTripId(1l);
        assertTrue(tourGroupEntityList.isEmpty());

    }
    }
