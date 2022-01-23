package com.lantushenkoao.common;

import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;

public class JacksonJsonMessageConverter extends MappingJackson2MessageConverter{
    public static String JMS_TYPE_ID_HEADER_NAME = "_type";

    public JacksonJsonMessageConverter(){
        this.setTargetType(MessageType.TEXT);
        this.setTypeIdPropertyName("_type");
    }
}