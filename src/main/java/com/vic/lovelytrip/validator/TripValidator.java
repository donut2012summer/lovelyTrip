package com.vic.lovelytrip.validator;

import com.vic.lovelytrip.entity.TripEntity;
import org.springframework.stereotype.Component;

@Component
public class TripValidator extends BaseValidator {

    @Override
    public void validate(Object object) {
        validateRequiredFields((TripEntity) object);
    }

    private void validateRequiredFields(TripEntity tripEntity) {
        requiredNotBlank("trip.title", tripEntity.getTitle());
        requiredNotBlank("trip.description", tripEntity.getDescription());
        requiredNotBlank("trip.destination", tripEntity.getDestination());
    }
}
