package com.lantushenko.downloader.gateway;

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
public class FileRequestReplyGateway {

    Logger log = Logger.getLogger(FileRequestReplyGateway.class.getName());

    @Resource
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = FileQueryRequest.QUEUE_NAME)
    public void receiveMessageAndReply(
            @Header(JmsHeaders.MESSAGE_ID) String incomingMessageId,
            @Header(JmsHeaders.REPLY_TO) ActiveMQQueue replyTo,
            @Payload FileQueryRequest request) {
        log.info("Received request: " + request.getFileName());
        FileQueryReply reply = new FileQueryReply(request.getFileName() + "-reply");

        jmsTemplate.convertAndSend(replyTo, reply, m -> {
            m.setJMSCorrelationID(incomingMessageId);
            m.setJMSTimestamp(System.nanoTime());
            m.setJMSExpiration(JMSConfig.DEFAULT_JMS_TIMEOUT);
            return m;
        });
        log.info("Reply has been sent");
    }
}
