package com.vic.lovelytrip.service;


import com.vic.lovelytrip.dto.TripDto;
import com.vic.lovelytrip.entity.TourGroupEntity;
import com.vic.lovelytrip.entity.TripEntity;
import com.vic.lovelytrip.lib.MessageCodeEnum;
import com.vic.lovelytrip.lib.MessageInfoContainer;
import com.vic.lovelytrip.mapper.TripMapper;
import com.vic.lovelytrip.repository.TourGroupRepository;
import com.vic.lovelytrip.repository.TripRepository;
import com.vic.lovelytrip.service.impl.TripServiceImpl;
import com.vic.lovelytrip.validator.TripValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {


    private final Logger logger = LoggerFactory.getLogger(TripServiceTest.class);
    //Mockito uses reflection to inject mock instances into those fields at runtime.
    //If a field is declared final, it cannot be assigned via reflection — Java will throw an error or Mockito will silently fail to inject.

    @Mock
    private TripRepository mockTripRepository;

    @Mock
    private TourGroupRepository mockTourGroupRepository;

    @Mock
    private TripValidator mockTripValidator;

    @Mock
    private TripMapper mockTripMapper;


    @InjectMocks
    private TripServiceImpl tripService;

    private TripEntity tripEntity;

    private TripDto tripDto;

    @BeforeEach
    void setUp(){
        logger.info("=============== Start set up ===============");

        tripDto = new TripDto();
        tripEntity = new TripEntity();
    }

    // todo
    @Test
    void testGetTripByIdWithValidId() {

        logger.info("=============== Start testGetTripByIdWithValidId ===============");

        // arrange test source
        long tripId = 1L;
        String fieldName = "trip.id";

        tripDto.setId(tripId);
        tripEntity.setId(tripId);

        // stub
        when(mockTripValidator.validateNotBlank(eq(fieldName), eq(tripId), any())).thenReturn(true);
        when(mockTripRepository.findById(eq(tripId))).thenReturn(Optional.of(tripEntity));
        when(mockTripMapper.mapToDto(any(), any())).thenReturn(tripDto);
        when(mockTourGroupRepository.getAvailableTourGroupListByTripId(eq(tripId))).thenReturn(new ArrayList<>());

        // act
        TripDto returnedDto = (TripDto)tripService.getTripById(tripId, false);

        // capture
        ArgumentCaptor<TripEntity> entityCaptor = ArgumentCaptor.forClass(TripEntity.class);
        ArgumentCaptor<MessageInfoContainer> msgCaptor = ArgumentCaptor.forClass(MessageInfoContainer.class);

        verify(mockTripMapper).mapToDto(entityCaptor.capture(), msgCaptor.capture());

        TripEntity capturedTrip = entityCaptor.getValue();
        assertSame(tripEntity, capturedTrip);

        MessageInfoContainer capturedMessageInfoContainer = msgCaptor.getValue();
        assertTrue(capturedMessageInfoContainer.getMessageInfoList().isEmpty());

        // assert
        assertEquals(tripDto.getId(), returnedDto.getId());

        verify(mockTripValidator).validateNotBlank(eq(fieldName), eq(tripId), any());
        verify(mockTripRepository).findById(eq(tripId));

        logger.info("=============== End testGetTripByIdWithValidId ===============");

    }

    @Test
    void testGetTripByIdWithInvalidId() {

        logger.info("=============== Start testGetTripByIdWithInvalidId ===============");

        // arrange
        long tripId = 1L;

        // stub
        when(mockTripValidator.validateNotBlank(any(), any(), any())).thenReturn(true);
        when(mockTripRepository.findById(any())).thenReturn(Optional.empty());
        when(mockTripMapper.mapToDto(any(), any())).thenReturn(tripDto);

        // act
        tripService.getTripById(tripId, false);

        ArgumentCaptor<TripEntity> entityCaptor = ArgumentCaptor.forClass(TripEntity.class);
        ArgumentCaptor<MessageInfoContainer> msgCaptor = ArgumentCaptor.forClass(MessageInfoContainer.class);

        // verify
        verify(mockTripValidator).validateNotBlank(eq("trip.id"), eq(tripId), any());
        verify(mockTripRepository).findById(eq(tripId));
        verify(mockTripMapper).mapToDto(entityCaptor.capture(), any());
        verify(mockTripMapper).mapToDto(any(), msgCaptor.capture());

        // capture
        TripEntity capturedTrip = entityCaptor.getValue();
        MessageInfoContainer capturedMessageInfoContainer = msgCaptor.getValue();

        // assert
        assertNull(capturedTrip.getId());
        assertFalse(capturedMessageInfoContainer.getMessageInfoList().isEmpty());
        assertEquals(
                  MessageCodeEnum.DATA_NOT_FOUND.getMessageCode()
                , capturedMessageInfoContainer.getMessageInfoList().get(0).getMessageCode());

        logger.info("=============== End testGetTripByIdWithInvalidId ===============");
    }

    @Test
    void testGetTripByIdWithBlankId(){

        logger.info("=============== Start testGetTripByIdWithBlankId ===============");
        // stub
        when(mockTripValidator.validateNotBlank(eq("trip.id"), eq(tripEntity.getId()), any(MessageInfoContainer.class)))
                .thenAnswer(invocationOnMock -> {
                   MessageInfoContainer msgContainer = invocationOnMock.getArgument(2);
                   msgContainer.addMessage(MessageCodeEnum.REQUIRE_NOT_BLANK, "trip.id");
                   return false;
        });

        when(mockTripMapper.mapToDto(any(), any())).thenReturn(tripDto);

        // act
        tripService.getTripById(tripDto.getId(), false);


        ArgumentCaptor<TripEntity> entityCaptor = ArgumentCaptor.forClass(TripEntity.class);
        ArgumentCaptor<MessageInfoContainer> msgCaptor = ArgumentCaptor.forClass(MessageInfoContainer.class);

        verify(mockTripRepository, never()).findById(any());
        verify(mockTripMapper).mapToDto(entityCaptor.capture(), any());
        verify(mockTripMapper).mapToDto(any(), msgCaptor.capture());

        TripEntity capturedTrip = entityCaptor.getValue();
        MessageInfoContainer capturedMessageInfoContainer = msgCaptor.getValue();

        assertNull(capturedTrip.getId());
        assertEquals(
                MessageCodeEnum.REQUIRE_NOT_BLANK.getMessageCode()
                , capturedMessageInfoContainer.getMessageInfoList().get(0).getMessageCode()
        );
        assertEquals(1, capturedMessageInfoContainer.size());

        logger.info("=============== End testGetTripByIdWithBlankId ===============");
    }



}
