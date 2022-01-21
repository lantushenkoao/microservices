package com.lantushenko.downloader.gateway;

import com.lantushenko.api.FileEventDocument;
import com.lantushenkoao.common.CalendarMessagePostProcessor;
import com.lantushenkoao.common.destinations.DestinationResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.DeliveryMode;

@Component("fileMessageSenderGateway")
@Slf4j
public class FileMessageSenderGateway implements MessageHandler {


    @Resource
    private JmsTemplate jmsTemplate;
    @Resource
    private DestinationResolver destinationResolver;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        FileEventDocument event = (FileEventDocument) message.getPayload();
        log.info("Got a message {}. Sending...", event);
        CalendarMessagePostProcessor postProcessor = new CalendarMessagePostProcessor(DeliveryMode.PERSISTENT,
                null, FileEventDocument.class);
        jmsTemplate.convertAndSend(destinationResolver.resolve(event.getClass()), event, postProcessor);
        log.info("Message has been sent");
    }
}
