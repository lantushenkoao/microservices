package com.lantushenko.webapp.gateway;

import com.lantushenko.api.FileEventDocument;
import com.lantushenkoao.common.destinations.DestinationResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class FileEventListenerGateway {

    @Resource
    private DestinationResolver destinationResolver;

    @JmsListener(destination = "#{destinationResolver.resolveAddress(T(com.lantushenko.api.FileEventDocument))}")
    public void handleFileEvent(FileEventDocument fileEvent){
        log.info("Received message: {}", fileEvent);
    }
}
