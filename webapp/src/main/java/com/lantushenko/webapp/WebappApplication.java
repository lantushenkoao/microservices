package com.lantushenko.webapp;

import com.lantushenko.api.FileQueryReply;
import com.lantushenko.api.FileQueryRequest;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.jms.JmsOutboundGateway;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;

@SpringBootApplication
public class WebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebappApplication.class, args);
	}



//	@Resource
//	private Receiver receiver;

//	@Bean
//	public DefaultMessageListenerContainer responder(ActiveMQConnectionFactory activeMQConnectionFactory) {
//		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
//		container.setConnectionFactory(activeMQConnectionFactory);
//		container.setDestinationName(FileQueryRequest.QUEUE_NAME);
//		MessageListenerAdapter adapter = new MessageListenerAdapter(new Object() {
//
//			@SuppressWarnings("unused")
//			public FileQueryReply handleMessage(FileQueryRequest order) {
//				return receiver.receiveMessage(order);
//			}
//
//		});
//		container.setMessageListener(adapter);
//		return container;
//	}

}
