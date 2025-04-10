package com.vic.lovelytrip.lib;

import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author Victoria Chang
 */
@Getter
@Setter
public class MessageInfoContainer {

    /* Collection to store message code and message pair */
    private List<MessageInfo> messageInfoList;


    /**
     * Initialize a map for containing messages
     *
     * @param
     * @return
     * @remark
     * */
    public MessageInfoContainer(){
        this.messageInfoList = new ArrayList<>();
    }

    /**
     * Add formatted message in the MessageContainer
     *
     * @param fieldNames String[]
     * @return
     * @remark
     * */
    public void addMessage(MessageCodeEnum messageCodeEnum, String... fieldNames) {

        String message = this.formattedMessage(messageCodeEnum, fieldNames);

        MessageInfo messageInfo = new MessageInfo(messageCodeEnum.getMessageCode(), message);

        if (!message.isBlank()){
            this.messageInfoList.add(messageInfo);

        }else{
            // log warn
        }
    }

    /**
     * Formated message with multiple field names
     *
     * @param fieldNames field names which need to be concat
     * @return String formatted message with field names e.g., "name, address, field invalid"
     * @remark
     * */
    private String formattedMessage(MessageCodeEnum messageCodeEnum, String... fieldNames){

        String concatedFieldName = "";

        if (fieldNames == null || fieldNames.length == 0){
            return concatedFieldName;
        }

        concatedFieldName = Arrays.stream(fieldNames)
                .filter(Objects::nonNull)
                .collect(Collectors.joining(", "));

        return MessageFormat.format(messageCodeEnum.getMessage(), concatedFieldName);
    }

    /**
     * Return the numbers of messages inside this container
     *
     * @param
     * @return numbers of messages inside this container
     * @remark
     * */
    public int size(){
        return this.messageInfoList.size();
    }

}
