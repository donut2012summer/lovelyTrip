package com.vic.lovelytrip.mapper;

import com.vic.lovelytrip.dto.TourGroupDto;
import com.vic.lovelytrip.entity.TourGroupEntity;
import com.vic.lovelytrip.repository.TourGroupRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TourGroupMapperTest {

    private final Logger logger = LoggerFactory.getLogger(TourGroupMapperTest.class);

    private TourGroupRepository tourGroupRepository;

    private TourGroupMapper tourGroupMapper;

    @Autowired
    public TourGroupMapperTest(TourGroupRepository tourGroupRepository, TourGroupMapper tourGroupMapper) {
        this.tourGroupRepository = tourGroupRepository;
        this.tourGroupMapper = tourGroupMapper;
    }


    @Test
    void testBatchToDto(){

        // arrange
        List<TourGroupEntity> tourGroupEntityList = tourGroupRepository.getAvailableTourGroupListByTripId(2l);
        List<TourGroupDto> tourGroupDtoList = tourGroupMapper.batchMapToDto(tourGroupEntityList);


        for (int i = 0; i < tourGroupDtoList.size(); i++) {
            assertEquals(tourGroupDtoList.get(i).getTripId(), tourGroupEntityList.get(i).getTripId());
            assertEquals(1, tourGroupEntityList.get(i).getStatus());
            logger.info("id" + tourGroupEntityList.get(i).getId());
        }
    }
}
