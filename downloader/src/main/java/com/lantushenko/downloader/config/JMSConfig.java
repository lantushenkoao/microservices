package com.lantushenko.downloader.config;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class JMSConfig {

    @Value("${spring.artemis.broker-url}")
    private String brokerURL;
    @Value("${spring.artemis.user}")
    private String user;
    @Value("${spring.artemis.password}")
    private String password;

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    @Bean
    public ActiveMQConnectionFactory artemisSSLConnectionFactory() {
        ActiveMQConnectionFactory artemisConnectionFactory = new ActiveMQConnectionFactory(brokerURL);
        artemisConnectionFactory.setUser(user);
        artemisConnectionFactory.setPassword(password);
        return artemisConnectionFactory;
    }

}
