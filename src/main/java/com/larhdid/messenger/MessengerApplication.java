package com.larhdid.messenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Hooks;

@SpringBootApplication
public class MessengerApplication {

	public static void main(String[] args) {
		Hooks.onErrorDropped((throwable)->{});
		SpringApplication.run(MessengerApplication.class, args);
	}

}
