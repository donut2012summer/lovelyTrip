package com.vic.lovelytrip.common;

import com.vic.lovelytrip.common.message.MessageCodeEnum;
import com.vic.lovelytrip.common.message.MessageInfoContainer;
import org.junit.jupiter.api.Test;

public class MessageInfoContainerTest {


    /**
     * Test to format message with concat field name
     *
     * @param
     * @return
     * @remark
     * */
    @Test
    void testWithMultipleFields(){
        MessageInfoContainer messageInfoContainer = new MessageInfoContainer();

        messageInfoContainer.addMessage(MessageCodeEnum.DATA_NOT_FOUND, "hey", "B", "C");
        System.out.println(messageInfoContainer.getMessageInfoList());
    }

}
