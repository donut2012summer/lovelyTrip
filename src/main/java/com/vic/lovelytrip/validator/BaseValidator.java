package com.vic.lovelytrip.validator;

import com.vic.lovelytrip.entity.BaseEntity;
import com.vic.lovelytrip.lib.HttpStatusEnum;
import com.vic.lovelytrip.lib.MessageInfoContainer;
import com.vic.lovelytrip.lib.MessageCodeEnum;
import com.vic.lovelytrip.lib.PatternEnum;
import lombok.Getter;

import java.util.Objects;

@Getter
public abstract class BaseValidator {

    /**
     * Add error message if the field value is empty or null
     *
     * @param fieldName
     * @param value
     * @return
     * @remark
     * */
    protected boolean addMessageWhenFieldBlank(String fieldName, Object value, MessageInfoContainer messageInfoContainer) {

        boolean isBlank = isBlankField(value);

        if(isBlank){
            messageInfoContainer.addMessage(MessageCodeEnum.REQUIRE_NOT_BLANK, fieldName);
            return true;
        }
        return false;
    }


    /**
     * Check format of target string with the PatternEnum, add message if they do not match
     *
     * @param fieldName
     * @param value of the field
     * @param patternEnum to be checked
     * @return true if the entity data is not empty
     * @remark
     * */
    protected void checkFormat(String fieldName, Object value, PatternEnum patternEnum, MessageInfoContainer messageInfoContainer) {

        addMessageWhenFieldBlank(fieldName, value, messageInfoContainer);

        if(messageInfoContainer.containsErrors()) {
            return;
        }

        if(!patternEnum.matches(value.toString())){
            messageInfoContainer.addMessage(MessageCodeEnum.INVALID_FORMAT, fieldName);
        }

    }

    protected void checkIdFormat(String fieldName, Long id, MessageInfoContainer messageInfoContainer) {

        addMessageWhenFieldBlank(fieldName, id, messageInfoContainer);

        if(messageInfoContainer.containsErrors()) {
            return;
        }

        if(id <= 0){
            messageInfoContainer.addMessage(MessageCodeEnum.INVALID_FORMAT, fieldName);
        }

    }

    protected boolean isBlankField(Object value){
        String stringValue = Objects.nonNull(value) ? String.valueOf(value) : "";
        return stringValue.isBlank();
    }



}
