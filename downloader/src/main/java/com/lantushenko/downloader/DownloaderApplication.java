package com.lantushenko.downloader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.util.logging.Logger;

@SpringBootApplication
@ImportResource("integration.xml")
public class DownloaderApplication {
	private static Logger log = Logger.getLogger(DownloaderApplication.class.getName());

	public static void main(String[] args) {
		log.info("Starting the application");
		SpringApplication.run(DownloaderApplication.class, args);
	}
}
