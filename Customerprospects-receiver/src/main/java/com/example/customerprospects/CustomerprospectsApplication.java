package com.example.customerprospects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
@EnableIntegration
public class CustomerprospectsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerprospectsApplication.class, args);
	}

}
