package com.lantushenko.downloader.services;

import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class HeartBeatService {
    private Logger log = Logger.getLogger(HeartBeatService.class.getName());
    public void sendMessage() throws InterruptedException {
        while(true){
            log.info("Sending heartbeat...");
            Thread.sleep(1000);
        }
    }
}
