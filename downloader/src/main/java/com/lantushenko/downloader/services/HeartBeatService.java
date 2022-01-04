package com.lantushenko.downloader.services;

import com.lantushenko.api.HeartbeatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class HeartBeatService {

    @Autowired
    private JmsTemplate jmsTemplate;
    private Logger log = Logger.getLogger(HeartBeatService.class.getName());
    private int counter = 0;

    @EventListener(ApplicationReadyEvent.class)
    public void sendMessage() throws InterruptedException {
        while (true) {
            log.info("Sending heartbeat...");
            HeartbeatMessage message = new HeartbeatMessage();
            message.setMessage("Sending heartbeat with counter value: " + counter++);
            jmsTemplate.convertAndSend(HeartbeatMessage.QUEUE_NAME, message);
            Thread.sleep(2000);
        }
    }
}
