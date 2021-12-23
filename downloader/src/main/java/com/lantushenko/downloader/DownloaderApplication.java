package com.lantushenko.downloader;

import com.lantushenko.downloader.services.HeartBeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.logging.Logger;

@SpringBootApplication
public class DownloaderApplication {
	private static Logger log = Logger.getLogger(DownloaderApplication.class.getName());

	@Resource
	private HeartBeatService heartBeatService;

	public static void main(String[] args) {
		log.info("Starting the application");
		SpringApplication.run(DownloaderApplication.class, args);
	}

	@Bean
	public ApplicationRunner sendHeartbeat(){
		return args -> heartBeatService.sendMessage();
	}

}
