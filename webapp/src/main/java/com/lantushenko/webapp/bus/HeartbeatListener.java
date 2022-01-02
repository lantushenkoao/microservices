package com.lantushenko.webapp.bus;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class HeartbeatListener {
    private Logger logger = Logger.getLogger(HeartbeatListener.class.getName());

    @JmsListener(destination = "microservice.heartbeat")
    public void listenHeartbeat(String message){
        logger.info("Heartbeat message received: " + message);
    }
}
