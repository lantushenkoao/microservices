package com.lantushenko.downloader.config;

import com.lantushenkoao.common.JacksonJsonMessageConverter;
import com.lantushenkoao.common.destinations.DestinationResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MessageConverter;

@Configuration
public class JMSConfig {

    public static final long DEFAULT_JMS_TIMEOUT = 10L;

    @Bean
    public DestinationResolver destinationResolver(){
        return new DestinationResolver();
    }
}
