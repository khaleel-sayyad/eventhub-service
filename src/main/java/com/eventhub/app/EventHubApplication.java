package com.eventhub.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.eventhub"})
public class EventHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventHubApplication.class, args);
	}

}
