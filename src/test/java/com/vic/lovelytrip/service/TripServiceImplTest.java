package com.vic.lovelytrip.service;

import com.vic.lovelytrip.TestDataFactory;
import com.vic.lovelytrip.dto.ImageCreateRequest;
import com.vic.lovelytrip.dto.TripCreateRequest;
import com.vic.lovelytrip.dto.TripCreateResponse;
import com.vic.lovelytrip.entity.ImageEntity;
import com.vic.lovelytrip.entity.TripEntity;
import com.vic.lovelytrip.lib.*;
import com.vic.lovelytrip.mapper.TripMapper;
import com.vic.lovelytrip.repository.ImageCrudRepository;
import com.vic.lovelytrip.repository.TripCrudRepository;
import com.vic.lovelytrip.validator.TripValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TripServiceImplTest {

    @Mock
    private TripCrudRepository tripCrudRepository;

    @Mock
    private ImageCrudRepository imageCrudRepository;

    @Mock
    private TripValidator tripValidator;

    private TripServiceImpl tripServiceImpl;

    @BeforeEach
    void setUp(){
        tripServiceImpl = new TripServiceImpl(
                tripCrudRepository,
                tripValidator,
                new TripMapper(),
                imageCrudRepository,

                // tourGroup not use, set null
                null
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

        when(tripCrudRepository.save(any())).thenAnswer(invocationOnMock -> {
            TripEntity tripEntity = (TripEntity) invocationOnMock.getArgument(0);
            tripEntity.setId(1L);
            tripEntity.setCreatedTime(OffsetDateTime.now());
            return tripEntity;
        });

        when(imageCrudRepository.saveAll(any())).thenAnswer(invocationOnMock -> {
            return (List<ImageEntity>) invocationOnMock.getArgument(0);
        });

        // act
        TripCreateResponse tripCreateResponse = tripServiceImpl.createTrip(tripCreateRequest);

        // assert
        assertEquals(1L, tripCreateResponse.getId());
        assertNotNull(tripCreateResponse.getCreatedTime());
        verify(tripCrudRepository).save(any());
        verify(imageCrudRepository).saveAll(any());
        verify(tripValidator).validateCreateTripRequest(tripCreateRequest);

        ArgumentCaptor<List<ImageEntity>> imageCaptor = ArgumentCaptor.forClass(List.class);
        verify(imageCrudRepository).saveAll(imageCaptor.capture());
        assertEquals(2, imageCaptor.getValue().size());

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

        verifyNoMoreInteractions(tripCrudRepository, imageCrudRepository);
    }
}
