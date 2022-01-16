package com.lantushenko.webapp.bus;

import com.lantushenko.api.FileEventMessage;
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

    @JmsListener(destination = "#{destinationResolver.resolveAddress(T(com.lantushenko.api.FileEventMessage))}")
    public void handleFileEvent(FileEventMessage fileEvent){
        log.info("Received message: {}", fileEvent);
    }
}
