package com.vic.lovelytrip.service;

import com.vic.lovelytrip.TestDataFactory;
import com.vic.lovelytrip.common.enums.ImageReferenceTableEnum;
import com.vic.lovelytrip.common.exception.BusinessException;
import com.vic.lovelytrip.common.message.MessageInfoContainer;
import com.vic.lovelytrip.dto.TripCreateRequest;
import com.vic.lovelytrip.dto.TripCreateResponse;
import com.vic.lovelytrip.dto.TripDetailResponse;
import com.vic.lovelytrip.entity.ImageEntity;
import com.vic.lovelytrip.entity.TripEntity;
import com.vic.lovelytrip.mapper.TripMapper;
import com.vic.lovelytrip.repository.ImageRepository;
import com.vic.lovelytrip.repository.TourGroupCrudRepository;
import com.vic.lovelytrip.repository.TripRepository;
import com.vic.lovelytrip.validator.TripValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TripServiceImplTest {

    @Mock
    private TripRepository tripRepository;

    @Mock
    private ImageRepository imageRepository;

    @Mock
    private TripValidator tripValidator;

    @Mock
    private TourGroupCrudRepository tourGroupCrudRepository;

    @Mock
    private ImageService imageService;

    private TripServiceImpl tripServiceImpl;

    @BeforeEach
    void setUp(){
        tripServiceImpl = new TripServiceImpl(
                tripRepository,
                tripValidator,
                new TripMapper(),
                imageRepository,
                tourGroupCrudRepository,
                imageService
        );
    }

    @Test
    void createTrip_withValidTripRequest_shouldContainValidIdAndCreatedTimeInResponse() {

        // arrange
        TripCreateRequest tripCreateRequest = TestDataFactory.getValidTripCreateRequest();
        MessageInfoContainer mockContainer = mock(MessageInfoContainer.class);


        // stub
        when(tripValidator.validateCreateTripRequest(tripCreateRequest)).thenReturn(mockContainer);
        doNothing().when(mockContainer).throwIfContainsErrors(any());

        when(tripRepository.save(any())).thenAnswer(invocationOnMock -> {
            TripEntity tripEntity = (TripEntity) invocationOnMock.getArgument(0);
            tripEntity.setId(1L);
            tripEntity.setCreatedTime(OffsetDateTime.now());
            return tripEntity;
        });

        when(imageRepository.saveAll(any())).thenAnswer(invocationOnMock -> {
            return (List<ImageEntity>) invocationOnMock.getArgument(0);
        });


        // act
        TripCreateResponse tripCreateResponse = tripServiceImpl.createTrip(tripCreateRequest);

        // assert
        assertEquals(1L, tripCreateResponse.getId());
        assertNotNull(tripCreateResponse.getCreatedTime());
        verify(tripRepository).save(any());
        verify(imageRepository).saveAll(any());
        verify(tripValidator).validateCreateTripRequest(tripCreateRequest);
    }

    @Test
    void createTrip_withInvalidTripRequest_shouldThrowException(){

        // arrange
        // stub validateCreateTripRequest. return mockMessageInfoContainer
        TripCreateRequest tripCreateRequest = TestDataFactory.getInvalidFormatTripCreateRequest();
        MessageInfoContainer mockContainer = mock(MessageInfoContainer.class);

        when(tripValidator.validateCreateTripRequest(tripCreateRequest)).thenReturn(mockContainer);
        // stub throw exception need to be catched by verify
        doThrow(BusinessException.class).when(mockContainer).throwIfContainsErrors(any());

        // act & assert
        assertThrows(BusinessException.class,
                () -> tripServiceImpl.createTrip(tripCreateRequest)
        );

        verifyNoMoreInteractions(tripRepository, imageRepository);
    }

    @Test
    void getTripDetailById_withValidTripId_shouldReturnCorrectTripDetail() {

        // arrange
        long tripId = 1L;
        int referenceId = 1;
        boolean includeTourGroups = true;

        doNothing().when(tripValidator).checkTripIdFormat(eq(tripId), any(MessageInfoContainer.class));
        when(tripRepository.findById(eq(tripId))).thenReturn(Optional.of(TestDataFactory.getValidTripEntity()));
        when(imageRepository.findByReferenceIdAndReferenceTable(eq(tripId), eq(referenceId))).thenReturn(TestDataFactory.getValidImageEntityList());
        when(tourGroupCrudRepository.findByTripId(eq(tripId))).thenReturn(Collections.emptyList());

        // act
        TripDetailResponse response = tripServiceImpl.getTripDetailById(tripId,includeTourGroups);


        // assert
        assertEquals(tripId, response.getId());
        assertNotNull(response.getImageList());
        assertEquals(1, response.getImageList().size());
        verify(tripValidator).checkTripIdFormat(eq(tripId), any(MessageInfoContainer.class));
        verify(imageRepository).findByReferenceIdAndReferenceTable(eq(tripId), eq(referenceId));
        verify(tourGroupCrudRepository).findByTripId(eq(tripId));
    }
}
