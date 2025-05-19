package com.vic.lovelytrip.validator;

import com.vic.lovelytrip.TestDataFactory;
import com.vic.lovelytrip.dto.TripCreateRequest;
import com.vic.lovelytrip.common.message.MessageCodeEnum;
import com.vic.lovelytrip.common.message.MessageInfo;
import com.vic.lovelytrip.common.message.MessageInfoContainer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TripValidatorTest {

    @Mock
    private LocationValidator locationValidator;

    @Mock
    private SupplierValidator supplierValidator;

    @InjectMocks
    private TripValidator tripValidator;


    @Test
    void validateCreateTripRequest_withValidRequest_shouldNotAddAnyError(){
        // arrange
        TripCreateRequest request = TestDataFactory.getValidTripCreateRequest();
        doNothing().when(locationValidator).validateLocationId(any(), any());

        // act
        MessageInfoContainer container = tripValidator.validateCreateTripRequest(request);

        // assert
        assertEquals(0, container.size());
        verify(locationValidator).validateLocationId(any(), any());
        verify(supplierValidator).validateSupplierId(eq(request.getSupplierId()), any());
    }

    @Test
    void validateTripCreateRequest_withInValidRequestExcludedSupplierId_shouldAddErrors(){

        // arrange
        TripCreateRequest request = TestDataFactory.getInvalidFormatTripCreateRequest();

        // stub locationValidator.validateLocationId
        doAnswer(invocationOnMock -> {
            // get arguments
            long id = invocationOnMock.getArgument(0);
            MessageInfoContainer container = invocationOnMock.getArgument(1);

            if (id <= 0){
                container.addMessage(MessageCodeEnum.INVALID_FORMAT, "Location ID");
            }
                
            return null;
            
        }).when(locationValidator).validateLocationId(anyLong(), any());

        // exclude supplier validation from this test — test it separately
        doNothing().when(supplierValidator).validateSupplierId(eq(request.getSupplierId()), any());

        // act
        List<MessageInfo> messageInfoList = tripValidator.validateCreateTripRequest(request).getMessageInfoList();

        // assert
        assertEquals(5, messageInfoList.size());

        // errors based on TestDataFactory.getInvalidFormatTripCreateRequest();
        assertEquals(MessageCodeEnum.INVALID_FORMAT.getMessageCode(), messageInfoList.get(0).getMessageCode());
        assertEquals(MessageCodeEnum.REQUIRE_NOT_BLANK.getMessageCode(), messageInfoList.get(1).getMessageCode());
        assertEquals(MessageCodeEnum.INVALID_FORMAT.getMessageCode(), messageInfoList.get(2).getMessageCode());
        assertEquals(MessageCodeEnum.INVALID_FORMAT.getMessageCode(), messageInfoList.get(3).getMessageCode());
        assertEquals(MessageCodeEnum.REQUIRE_AT_LEAST_ONE.getMessageCode(), messageInfoList.get(4).getMessageCode());


    }


}
