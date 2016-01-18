package com.tii.microservices.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;

/**
 * All you need to run a Eureka registration server.
 * 
 */
@SpringBootApplication
@EnableEurekaServer
@ComponentScan(useDefaultFilters = false)
public class RegistrationServer {

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for registration.properties or registration.yml
		//System.setProperty("spring.cloud.config.name", "registration-server");

		SpringApplication.run(RegistrationServer.class, args);
	}
}
