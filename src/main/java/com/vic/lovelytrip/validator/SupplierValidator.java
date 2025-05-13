package com.vic.lovelytrip.validator;

import com.vic.lovelytrip.lib.MessageCodeEnum;
import com.vic.lovelytrip.lib.MessageInfoContainer;
import com.vic.lovelytrip.repository.SupplierCrudRepository;
import org.springframework.stereotype.Component;

@Component
public class SupplierValidator extends BaseValidator {

    SupplierCrudRepository supplierCrudRepository;

    SupplierValidator(SupplierCrudRepository supplierCrudRepository) {
        this.supplierCrudRepository = supplierCrudRepository;
    }
    /**
     * validate supplier id and add message if supplier id does not exist or is blank
     * @param supplierId
     * @param messageInfoContainer
     * @return MessageInfoContainer
     * @remark
     * */
    public void validateSupplierId(long supplierId, MessageInfoContainer messageInfoContainer) {

        checkIdFormat("Supplier ID", supplierId, messageInfoContainer);

        if(messageInfoContainer.containsErrors()){
            return;
        }

        if(!supplierCrudRepository.existsById(supplierId)){
            messageInfoContainer.addMessage(MessageCodeEnum.DATA_NOT_FOUND, "Supplier");
        }
    }

}
