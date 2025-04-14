package com.vic.lovelytrip.validator;

import com.vic.lovelytrip.entity.BaseEntity;
import com.vic.lovelytrip.entity.TripEntity;
import com.vic.lovelytrip.lib.MessageCodeEnum;
import com.vic.lovelytrip.lib.MessageInfoContainer;
import com.vic.lovelytrip.repository.SupplierRepository;
import com.vic.lovelytrip.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TripValidator extends BaseValidator {

    private SupplierRepository supplierRepository;


    @Autowired
    public TripValidator(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public MessageInfoContainer validate(BaseEntity entity) {

        MessageInfoContainer messageInfoContainer = new MessageInfoContainer();

        TripEntity tripEntity = (TripEntity) entity;

        boolean isValidSupplier = validateSupplier(tripEntity.getSupplierId(), messageInfoContainer);

        if (!isValidSupplier) {
            return messageInfoContainer;
        }

        return checkRequiredFields(tripEntity, messageInfoContainer);
    }

    /**
     * validate supplier id and add message if supplier id does not exist
     * @param supplierId
     * @param messageInfoContainer
     * @return MessageInfoContainer
     * @remark
     * */
    protected boolean validateSupplier(long supplierId, MessageInfoContainer messageInfoContainer) {

        boolean isBlankId = !validateNotBlank("trip.supplierId", supplierId, messageInfoContainer);

        if (isBlankId) {
            return false;
        }

        if(supplierRepository.existsById(supplierId)){
            return true;
        }

        messageInfoContainer.addMessage(MessageCodeEnum.DATA_NOT_FOUND, "Supplier");
        return false;
    }

    /**
     * Validate the required fields in tripEntity, add messages in the super message container if they are empty
     *
     * @param tripEntity
     * @return
     * @remark
     * */
    private MessageInfoContainer checkRequiredFields(TripEntity tripEntity, MessageInfoContainer messageInfoContainer) {
        validateNotBlank("trip.title", tripEntity.getTitle(), messageInfoContainer);
        validateNotBlank("trip.description", tripEntity.getDescription(), messageInfoContainer);
        validateNotBlank("trip.destination", tripEntity.getDestination(), messageInfoContainer);
        validateNotBlank("trip.duration", tripEntity.getDuration(), messageInfoContainer);

        return messageInfoContainer;
    }

}
