package com.lantushenko.downloader;

import com.lantushenko.downloader.services.HeartBeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import java.util.logging.Logger;

@SpringBootApplication
public class DownloaderApplication {
	private static Logger log = Logger.getLogger(DownloaderApplication.class.getName());

	public static void main(String[] args) {
		log.info("Starting the application");
		SpringApplication.run(DownloaderApplication.class, args);
	}

	@Bean // Serialize message content to json using TextMessage
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}
}
