package com.cos.mariademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MariademoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MariademoApplication.class, args);
	}

}
