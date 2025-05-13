package com.vic.lovelytrip.validator;

import com.vic.lovelytrip.dto.TripCreateRequest;
import com.vic.lovelytrip.lib.MessageCodeEnum;
import com.vic.lovelytrip.lib.MessageInfoContainer;
import com.vic.lovelytrip.lib.PatternEnum;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;

@Component
public class TripValidator extends BaseValidator {

    private SupplierValidator supplierValidator;

    private LocationValidator locationValidator;

    public TripValidator(SupplierValidator supplierValidator, LocationValidator locationValidator) {
        this.supplierValidator = supplierValidator;
        this.locationValidator = locationValidator;
    }

    /**
     * validate content of CreateTripRequest
     * @param request
     * @return MessageInfoContainer
     * @remark
     * */
    public MessageInfoContainer validateCreateTripRequest(TripCreateRequest request) {

        MessageInfoContainer messageInfoContainer = new MessageInfoContainer();

        supplierValidator.validateSupplierId(request.getSupplierId(), messageInfoContainer);

        if (messageInfoContainer.containsErrors()) {
            return messageInfoContainer;
        }

        checkRequiredFields(request, messageInfoContainer);

        return messageInfoContainer;
    }

    public void checkTripIdFormat(Long tripId, MessageInfoContainer messageInfoContainer) {
        checkIdFormat("trip.id", tripId, messageInfoContainer);
    }

    /**
     * Validate the required fields in tripEntity, add messages in the super message container if they are empty
     *
     * @param request
     * @return
     * @remark
     * */
    private MessageInfoContainer checkRequiredFields(TripCreateRequest request, MessageInfoContainer messageInfoContainer) {

        // title
        checkFormat("title", request.getTitle(), PatternEnum.TITLE, messageInfoContainer);

        // description
        checkFormat("description", request.getDescription(), PatternEnum.DESCRIPTION, messageInfoContainer);

        // min duration
        boolean minDurationIsEmpty = addMessageWhenFieldBlank("trip.min duration", request.getMinDuration(), messageInfoContainer);

        if (!minDurationIsEmpty && request.getMinDuration() <= 0){
            messageInfoContainer.addMessage(MessageCodeEnum.INVALID_FORMAT, "min duration");
        }

        // location id
        locationValidator.validateLocationId(request.getMainLocationId(), messageInfoContainer);

        // image list
        if (CollectionUtils.isEmpty(request.getImageCreateRequestList())){
            messageInfoContainer.addMessage(MessageCodeEnum.REQUIRE_AT_LEAST_ONE, "image");
        }

        return messageInfoContainer;
    }

}
