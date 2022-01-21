package com.lantushenkoao.common;

import com.lantushenko.api.FileQueryReply;
import lombok.AllArgsConstructor;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.jms.core.MessagePostProcessor;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

@AllArgsConstructor
public class CalendarMessagePostProcessor implements MessagePostProcessor {

    private int deliveryMode;
    private Destination replyTo;

    @Override
    public Message postProcessMessage(Message message) throws JMSException {

        message.setJMSDeliveryMode(deliveryMode);
        if (null != replyTo) {
            message.setJMSReplyTo(replyTo);
        }
        message.setJMSTimestamp(System.nanoTime());
        message.setStringProperty("XMessageType", message.getClass().toString());
        return message;
    }
}