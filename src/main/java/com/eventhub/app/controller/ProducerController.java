package com.eventhub.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eventhub.app.service.MessageProducer;

@RestController
public class ProducerController {
	@Autowired
	MessageProducer producerService;

	@PostMapping("/publish")
	public void sendMessageToKafkaTopic(@RequestBody String message) {
		this.producerService.sendMessage(message);
	}
}
