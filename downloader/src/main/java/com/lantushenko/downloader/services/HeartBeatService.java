package com.lantushenko.downloader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class HeartBeatService {

    @Autowired
    private JmsTemplate jmsTemplate;
    private Logger log = Logger.getLogger(HeartBeatService.class.getName());
    private int counter = 0;

    public void sendMessage() throws InterruptedException {
        while (true) {
            log.info("Sending heartbeat...");
            jmsTemplate.convertAndSend("microservice.heartbeat", "heartbeat message. Iteration: " + counter++);
            Thread.sleep(1000);
        }
    }
}
