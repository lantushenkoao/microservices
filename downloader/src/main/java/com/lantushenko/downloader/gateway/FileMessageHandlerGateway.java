package com.lantushenko.downloader.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FileMessageHandlerGateway implements MessageHandler {
    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        log.info("Got a message. Sending...");
    }
}
