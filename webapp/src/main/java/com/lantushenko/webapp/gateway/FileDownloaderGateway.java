package com.lantushenko.webapp.gateway;

import com.lantushenko.api.FileQueryReply;
import com.lantushenko.api.FileQueryRequest;
import com.lantushenko.webapp.config.JMSConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.UUID;

//https://aniruthmp.github.io/Spring-JMS-request-response/
//https://memorynotfound.com/spring-jms-setting-reading-header-properties-example/
//https://stackoverflow.com/questions/53506177/how-to-use-jmstemplate-sendandreceive
@Service
@Slf4j
public class FileDownloaderGateway {

    @Resource
    private JmsTemplate jmsTemplate;
    @Resource
    private MessageConverter messageConverter;

    public void requestFile() throws JMSException {
        log.info("Requesting file...");
        FileQueryRequest request = new FileQueryRequest("testFile1.txt");
        request.setFileName("SomeFile.txt");
        jmsTemplate.setReceiveTimeout(JMSConfig.DEFAULT_JMS_TIMEOUT);
        Message msg = jmsTemplate.sendAndReceive(FileQueryRequest.QUEUE_NAME, session -> {
            Message message = messageConverter.toMessage(request, session);
            message.setJMSReplyTo(new ActiveMQQueue(FileQueryReply.QUEUE_NAME));
            message.setJMSExpiration(JMSConfig.DEFAULT_JMS_TIMEOUT);
            message.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT);
            message.setJMSTimestamp(System.nanoTime());

            return message;
        });
        FileQueryReply reply = (FileQueryReply) messageConverter.fromMessage(msg);
        log.info("Reply received: " + reply);
    }

}
