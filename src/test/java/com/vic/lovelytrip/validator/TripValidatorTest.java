package com.vic.lovelytrip.validator;

import com.vic.lovelytrip.lib.MessageInfoContainer;
import com.vic.lovelytrip.repository.SupplierRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class TripValidatorTest {

    private final Logger logger = LoggerFactory.getLogger(TripValidatorTest.class);

    @Test
    void testValidateSupplierWithValidId(){

        logger.info("---------- testValidateSupplierWithValidId Start ----------");

        String fieldName = "trip.supplierId";
        MessageInfoContainer messageInfoContainer = new MessageInfoContainer();
        long supplierId = 1L;

        SupplierRepository mockSupplierRepository = Mockito.mock(SupplierRepository.class);
        TripValidator spyTripValidator = Mockito.spy(new TripValidator(mockSupplierRepository));

        doReturn(true).when(spyTripValidator).validateNotBlank(eq(fieldName), eq(supplierId), any(MessageInfoContainer.class));
        when(mockSupplierRepository.existsById(eq(supplierId))).thenReturn(true);

        boolean isValidSupplier = spyTripValidator.validateSupplier(supplierId, messageInfoContainer);

        assert messageInfoContainer.size() == 0;
        verify(mockSupplierRepository, times(1)).existsById(eq(supplierId));
        assertTrue(isValidSupplier);

        logger.info("---------- validated trip supplier with id {} passed ! ----------", supplierId);
    }

}
