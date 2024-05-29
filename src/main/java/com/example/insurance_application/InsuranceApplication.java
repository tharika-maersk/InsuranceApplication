package com.example.insurance_application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class InsuranceApplication {

	private static final Logger log = LoggerFactory.getLogger(InsuranceApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(InsuranceApplication.class, args);
		log.info("hello world");
	}

}
