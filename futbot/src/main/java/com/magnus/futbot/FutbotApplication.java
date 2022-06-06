package com.magnus.futbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class FutbotApplication {

	public static void main(String[] args) {
		SpringApplication.run(FutbotApplication.class, args);
	}

}
