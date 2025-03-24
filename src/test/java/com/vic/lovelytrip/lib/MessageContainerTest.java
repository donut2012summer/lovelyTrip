package com.vic.lovelytrip.lib;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MessageContainerTest {


    // testWithMultipleFields
    @Test
    void testWithMultipleFields(){
        MessageContainer messageContainer = new MessageContainer();

        messageContainer.addMessage(MessageCodeEnum.DATA_NOT_FOUND, "hey", "B", "C");
        System.out.println(messageContainer.getMessageContainer());
    }

    // test with null value



}
