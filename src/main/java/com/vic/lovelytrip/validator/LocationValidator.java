package com.vic.lovelytrip.validator;

import com.vic.lovelytrip.lib.HttpStatusEnum;
import com.vic.lovelytrip.lib.MessageCodeEnum;
import com.vic.lovelytrip.lib.MessageInfoContainer;
import com.vic.lovelytrip.lib.PatternEnum;
import com.vic.lovelytrip.repository.LocationCrudRepository;
import org.springframework.stereotype.Component;

@Component
public class LocationValidator extends BaseValidator {
    LocationCrudRepository locationCrudRepository;

    public LocationValidator(LocationCrudRepository locationCrudRepository) {
        this.locationCrudRepository = locationCrudRepository;
    }

    /**
     * validate location id and add message if supplier id does not exist or is blank
     * @param locationId
     * @param messageInfoContainer
     * @return MessageInfoContainer
     * @remark
     * */
    public void validateLocationId(Long locationId, MessageInfoContainer messageInfoContainer) {

        checkIdFormat("location ID", locationId, messageInfoContainer );

        if (messageInfoContainer.containsErrors()) {
            return;
        }

        if (!locationCrudRepository.existsById(locationId)) {
            messageInfoContainer.addMessage(MessageCodeEnum.DATA_NOT_FOUND, "Main Location");
        }
    }
}
