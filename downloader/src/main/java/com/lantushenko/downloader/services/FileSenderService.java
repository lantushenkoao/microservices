package com.lantushenko.downloader.services;

import com.lantushenko.api.FileQueryReply;
import com.lantushenko.api.FileQueryRequest;
import com.lantushenko.api.HeartbeatMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQObjectMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.logging.Logger;

@Service
public class FileSenderService {

    Logger log = Logger.getLogger(FileSenderService.class.getName());

    @Resource
    private MessageConverter jsonMessageConverter;

    public FileQueryReply receiveMessage(@Payload FileQueryRequest request) {
        log.info("Received request: " + request.getFileName());
        FileQueryReply reply = new FileQueryReply(request.getFileName() + "-reply");
        return reply;
    }

    @Bean
    public DefaultMessageListenerContainer responder(ActiveMQConnectionFactory activeMQConnectionFactory) {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(activeMQConnectionFactory);
        container.setDestinationName(FileQueryRequest.QUEUE_NAME);
        MessageListenerAdapter adapter = new MessageListenerAdapter(new Object() {
            public FileQueryReply handleMessage(FileQueryRequest request) {
                return receiveMessage(request);
            }
        });
        adapter.setMessageConverter(jsonMessageConverter);
        container.setMessageListener(adapter);
        return container;
    }
}
