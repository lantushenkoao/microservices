package com.lantushenko.downloader.services;

import com.lantushenko.api.FileQueryReply;
import com.lantushenko.api.FileQueryRequest;
import com.lantushenko.downloader.config.JMSConfig;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.JmsHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.logging.Logger;

@Service
public class FileSenderGateway {

    Logger log = Logger.getLogger(FileSenderGateway.class.getName());

    @Resource
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = FileQueryRequest.QUEUE_NAME)
    public void receiveMessageAndReply(
            @Header(JmsHeaders.CORRELATION_ID) String correlationId,
            @Header(JmsHeaders.REPLY_TO) ActiveMQQueue replyTo,
            @Payload FileQueryRequest request) {
        log.info("Received request: " + request.getFileName());
        FileQueryReply reply = new FileQueryReply(request.getFileName() + "-reply");

        jmsTemplate.convertAndSend(replyTo, reply, m -> {
            m.setJMSCorrelationID(correlationId);
            m.setJMSTimestamp(System.nanoTime());
            m.setJMSExpiration(JMSConfig.DEFAULT_JMS_TIMEOUT);
            return m;
        });
        log.info("Reply has been sent");
    }
}
