package com.lantushenko.webapp.bus;

import com.lantushenko.api.FileQueryReply;
import com.lantushenko.api.FileQueryRequest;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.UUID;
import java.util.logging.Logger;

//https://aniruthmp.github.io/Spring-JMS-request-response/
@Service
public class FileDownloader {
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Resource
    private MessageConverter messageConverter;

    private Logger logger = Logger.getLogger(FileDownloader.class.getName());

    public void requestFile() throws JMSException {
        logger.info("Requesting file...");
        FileQueryRequest request = new FileQueryRequest("testFile1.txt");
        request.setFileName("SomeFile.txt");
        Session session = jmsMessagingTemplate.getConnectionFactory().createConnection()
                .createSession(false, Session.AUTO_ACKNOWLEDGE);

        Message requestMessage = messageConverter.toMessage(request, session);
        requestMessage.setJMSCorrelationID(UUID.randomUUID().toString());
        requestMessage.setJMSReplyTo(new ActiveMQQueue(FileQueryReply.QUEUE_NAME));
        requestMessage.setJMSExpiration(1000L);
        requestMessage.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT);

        FileQueryReply reply = jmsMessagingTemplate.convertSendAndReceive(new ActiveMQQueue(FileQueryRequest.QUEUE_NAME),
                requestMessage, FileQueryReply.class);
        logger.info("File received: " + reply.getFile());
    }
}
