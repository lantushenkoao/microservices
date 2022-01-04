package com.lantushenko.webapp.bus;

import com.lantushenko.api.HeartbeatMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class HeartbeatListener {
    private Logger logger = Logger.getLogger(HeartbeatListener.class.getName());

    @JmsListener(destination = HeartbeatMessage.QUEUE_NAME)
    public void listenHeartbeat(HeartbeatMessage message){
        logger.info("Heartbeat message received: " + message.getMessage());
    }
}
