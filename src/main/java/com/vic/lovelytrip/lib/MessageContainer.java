package com.vic.lovelytrip.lib;

import lombok.Data;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class MessageContainer {

    private Map<String, String> messageContainer;

    public MessageContainer(){
        this.messageContainer = new HashMap<>();
    }

    // system should deal well, the fields cannot be null!
    public void addMessage(MessageCodeEnum code, String... fields) {

        String message = this.formattedMessage(code, fields);

        if (!message.isBlank()){
            this.messageContainer.put(code.getMessageCode(), message);
        }else{
            // log warn
        }
    }

    private String formattedMessage(MessageCodeEnum messageCodeEnum, String... fields){
        // check if the string is null or empty : return " "
        String concatedFieldName = "";
        if (fields == null || fields.length == 0){
            return concatedFieldName;
        }

        concatedFieldName = Arrays.stream(fields)
                .filter(Objects::nonNull)
                .collect(Collectors.joining(", "));
            // else concat the string

        return MessageFormat.format(messageCodeEnum.getMessage(), concatedFieldName);
    }

    public int size(){
        return this.messageContainer.size();
    }

}
