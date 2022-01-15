package com.lantushenkoao.common.destinations;

import com.lantushenko.api.FileEventMessage;
import com.lantushenkoao.common.exceptions.DestinationNotFoundException;
import org.apache.activemq.artemis.jms.client.ActiveMQDestination;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;

import java.util.HashMap;
import java.util.Map;

public class DestinationResolver {

    private Map<Class, ActiveMQDestination> destinationMap = new HashMap<>();

    public DestinationResolver(){
        destinationMap.put(FileEventMessage.class, new ActiveMQQueue("live.microservices.file.queues"));
    }

    public ActiveMQDestination resolve(Class messageClass){
        if(destinationMap.containsKey(messageClass)){
            return destinationMap.get(messageClass);
        }
        throw new DestinationNotFoundException(messageClass);
    }

    public String resolveAddress(Class messageClass) {
        return resolve(messageClass).getAddress();
    }
}