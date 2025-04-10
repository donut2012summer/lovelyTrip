package com.vic.lovelytrip.validator;

import com.vic.lovelytrip.entity.BaseEntity;
import com.vic.lovelytrip.lib.MessageInfoContainer;
import com.vic.lovelytrip.lib.MessageCodeEnum;
import com.vic.lovelytrip.lib.PatternEnum;
import lombok.Getter;

import java.util.Objects;

@Getter
public abstract class BaseValidator {

    /**
     * validate the entities, implemented by each validator
     *
     * @param baseEntity entity that need to be validated
     * @return
     * @remark
     * */
    abstract MessageInfoContainer validate(BaseEntity baseEntity);

    /**
     * Check field value and add message when it is empty
     *
     * @param fieldName
     * @param value
     * @return true if the entity data is not blank
     * @remark
     * */
    protected Boolean validatedNotBlank(String fieldName, Object value, MessageInfoContainer messageInfoContainer) {

        Boolean isBlank = isBlankField(value);

        if(isBlank){
            messageInfoContainer.addMessage(MessageCodeEnum.REQUIRE_NOT_BLANK, fieldName);
        }
        return !isBlank;
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

        if(validatedNotBlank(fieldName, value, messageInfoContainer) && !patternEnum.matches(value.toString())){
            messageInfoContainer.addMessage(MessageCodeEnum.INVALID_FORMAT, fieldName);
        }

    }

    protected boolean isBlankField(Object value){
        String stringValue = Objects.nonNull(value) ? String.valueOf(value) : "";
        return stringValue.isBlank();
    }



}
