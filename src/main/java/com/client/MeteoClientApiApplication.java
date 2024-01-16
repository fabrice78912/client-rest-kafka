package com.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MeteoClientApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeteoClientApiApplication.class, args);
	}

}
