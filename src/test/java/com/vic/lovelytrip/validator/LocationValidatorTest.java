package com.vic.lovelytrip.validator;


import com.vic.lovelytrip.common.message.MessageCodeEnum;
import com.vic.lovelytrip.common.message.MessageInfoContainer;
import com.vic.lovelytrip.repository.LocationCrudRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LocationValidatorTest {

    @Mock
    private LocationCrudRepository locationCrudRepository;

    @InjectMocks
    private LocationValidator locationValidator;

    private MessageInfoContainer container;

    @BeforeEach
    void setUp(){
        container = new MessageInfoContainer();
    }

    @Test
    void validateLocationId_withExistingValidLocationId_shouldNotAddAnyError() {

        // arrange
        long validLocationId = 1234L;

        when(locationCrudRepository.existsById(validLocationId)).thenReturn(true);

        // act
        locationValidator.validateLocationId(validLocationId, container);

        // assert
        assertTrue(container.getMessageInfoList().isEmpty());
        verify(locationCrudRepository).existsById(validLocationId);

    }

    @Test
    void validateLocationId_withInValidNegativeLocationId_shouldAddInvalidFormatError() {

        // arrange
        long invalidLocationId = -12L;

        // act
        locationValidator.validateLocationId(invalidLocationId, container);

        // assert
        assertEquals(1, container.getMessageInfoList().size());
        assertEquals(MessageCodeEnum.INVALID_FORMAT.getMessageCode(), container.getMessageInfoList().get(0).getMessageCode());
        verify(locationCrudRepository, never()).existsById(invalidLocationId);
    }

    @Test
    void validateLocationId_withNonExistingLocationId_shouldAddDataNotFoundError() {

        // arrange
        long invalidLocationId = 333L;

        // stub
        when(locationCrudRepository.existsById(invalidLocationId)).thenReturn(false);

        // act
        locationValidator.validateLocationId(invalidLocationId, container);

        // assert
        assertEquals(1, container.getMessageInfoList().size());
        assertEquals(MessageCodeEnum.DATA_NOT_FOUND.getMessageCode(), container.getMessageInfoList().get(0).getMessageCode());
        verify(locationCrudRepository).existsById(invalidLocationId);

    }

    @Test
    void validateLocationId_withBlankLocationId_shouldAddRequiredNotBlankError() {

        // arrange
        Long nullLocationId = null;
        // act
        locationValidator.validateLocationId(nullLocationId, container);

        // assert
        assertEquals(1, container.getMessageInfoList().size());
        assertEquals(MessageCodeEnum.REQUIRE_NOT_BLANK.getMessageCode(), container.getMessageInfoList().get(0).getMessageCode());
        verify(locationCrudRepository, never()).existsById(nullLocationId);
    }
}