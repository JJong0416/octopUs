package com.octopus;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableBatchProcessing
@SpringBootApplication
public class OctopusApplication {

	public static void main(String[] args) {
		SpringApplication.run(OctopusApplication.class, args);
	}

}
