package com.vic.lovelytrip.validator;

import com.vic.lovelytrip.entity.TripEntity;
import com.vic.lovelytrip.lib.MessageInfoContainer;
import com.vic.lovelytrip.repository.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class TripValidatorTest {

//    @Mock
//    private SupplierRepository supplierRepository;

    @Test
    void testValidateSupplierWithValidId(){

        // prepare valid test data
        String fieldName = "trip.supplierId";
        long supplierId = 1L;
        MessageInfoContainer messageInfoContainer = new MessageInfoContainer();

        // create mock object or spy object for test contains private method
        SupplierRepository supplierRepositoryMock = Mockito.mock(SupplierRepository.class);
        TripValidator tripValidatorSpy = Mockito.spy(new TripValidator(supplierRepositoryMock));

            // mock object :  tripValidator.validateSupplier
            // mockObject  :  supplierRepository

        // define stubbing of the methods inside the test method
            // validatedNotBlank("trip.supplierId", supplierId, messageInfoContainer);
        doReturn(true).when(tripValidatorSpy).validatedNotBlank(eq(fieldName), eq(supplierId), any(MessageInfoContainer.class));
            // supplierRepository.existsById(supplierId) > false
        when(supplierRepositoryMock.existsById(supplierId)).thenReturn(false);

        // execute test method
        messageInfoContainer = tripValidatorSpy.validateSupplier(supplierId, messageInfoContainer);
            // tripValidatorSpy.validateSupplier(supplierId, messageContainer)
        // make assertion
        assert messageInfoContainer.size() == 0;

        // verify action



    }

//    @Test
//    void validateSupplierWithValidId(){
//        // arrange
//            // correct supplier id
//        // arrange test sources
//        Long supplierId = 1L;
//        MessageInfoContainer messageInfoContainer = new MessageInfoContainer();
//
//        // Mock validator logic
//        // 1. Create Mock / spy object
//        tripValidator = Mockito.spy(new TripValidator(supplierRepository));
//
//        // 2. Stub method
//        doReturn(true).when(tripValidator).validatedNotBlank(eq("trip.supplierId"), eq(supplierId), any(MessageInfoContainer.class));
//        when(supplierRepository.existsById(supplierId)).thenReturn(true);
//
//        // 3. call test methods
//        MessageInfoContainer result = tripValidator.validateSupplier(supplierId, messageInfoContainer);
//
//        // 4. assert result
//        assertTrue(result.getMessageInfoList().isEmpty());
//        verify(supplierRepository, times(1)).existsById(eq(supplierId));
//
//    }

    // test validateSupplierWithValidData
        // when validatedNotBlank > true
        // when supplierRepository.existsById > true
        // assert message container is empty
        // verify supplierRepository.existsById is called


    // set up a TripEntity
    // mock return object for validateSupplier



}
