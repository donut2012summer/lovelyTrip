package com.vic.lovelytrip.validator;

import com.vic.lovelytrip.entity.BaseEntity;
import com.vic.lovelytrip.entity.TripEntity;
import com.vic.lovelytrip.lib.MessageCodeEnum;
import com.vic.lovelytrip.lib.MessageInfoContainer;
import com.vic.lovelytrip.repository.SupplierRepository;
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

        messageInfoContainer = validateSupplier(tripEntity.getSupplierId(), messageInfoContainer);

        if (messageInfoContainer.size() > 0) {
            return messageInfoContainer;
        }

        return validateRequiredFields(tripEntity, messageInfoContainer);
    }

    /**
     * validate supplier id and add message if supplier id does not exist
     * @param supplierId
     * @param messageInfoContainer
     * @return MessageInfoContainer
     * @remark
     * */
    protected MessageInfoContainer validateSupplier(long supplierId, MessageInfoContainer messageInfoContainer) {

        Boolean isNotBlank = validatedNotBlank("trip.supplierId", supplierId, messageInfoContainer);

        if( isNotBlank && !supplierRepository.existsById(supplierId)){
            messageInfoContainer.addMessage(MessageCodeEnum.SUPPLIER_NOT_FOUND);
        }
        return messageInfoContainer;
    }
    /**
     * Validate the required fields in tripEntity, add messages in the super message container if they are empty
     *
     * @param tripEntity
     * @return
     * @remark
     * */
    private MessageInfoContainer validateRequiredFields(TripEntity tripEntity, MessageInfoContainer messageInfoContainer) {
        validatedNotBlank("trip.title", tripEntity.getTitle(), messageInfoContainer);
        validatedNotBlank("trip.description", tripEntity.getDescription(), messageInfoContainer);
        validatedNotBlank("trip.destination", tripEntity.getDestination(), messageInfoContainer);
        validatedNotBlank("trip.duration", tripEntity.getDuration(), messageInfoContainer);
        return messageInfoContainer;
    }

}
