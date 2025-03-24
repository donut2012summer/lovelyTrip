package com.vic.lovelytrip.validator;

import com.vic.lovelytrip.lib.MessageCodeEnum;
import com.vic.lovelytrip.lib.MessageContainer;
import com.vic.lovelytrip.lib.PatternEnum;
import lombok.Getter;

import java.util.Objects;

@Getter
public abstract class BaseValidator {

    MessageContainer messageContainer;

    BaseValidator() {
        this.messageContainer = new MessageContainer();
    }

    abstract void validate(Object object);

    Boolean requiredNotBlank(String field, Object value){

        Boolean isInvalid = isBlankField(value);

        if(isInvalid){
            this.messageContainer.addMessage(MessageCodeEnum.REQUIRE_NOT_BLANK, field);
        }
        return !isInvalid;
    }

    private void checkFormat(String field, Object value, PatternEnum patternEnum){

        if(requiredNotBlank(field, value) && !patternEnum.matches(value.toString())){
            this.messageContainer.addMessage(MessageCodeEnum.INVALID_FORMAT, field);
        }

    }

    private boolean isBlankField(Object value){
        String stringValue = Objects.nonNull(value) ? String.valueOf(value) : "";
        return stringValue.isBlank();
    }



}
