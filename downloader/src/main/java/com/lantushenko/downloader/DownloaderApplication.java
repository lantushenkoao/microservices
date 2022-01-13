package com.lantushenko.downloader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class DownloaderApplication {
	private static Logger log = Logger.getLogger(DownloaderApplication.class.getName());

	public static void main(String[] args) {
		log.info("Starting the application");
		SpringApplication.run(DownloaderApplication.class, args);
	}
}
