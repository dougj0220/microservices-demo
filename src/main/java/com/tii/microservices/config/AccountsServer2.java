package com.tii.microservices.config;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

/**
 * Run as a micro-service, registering with the Discovery Server (Eureka).
 * <p>
 * Note that the configuration for this application is imported from
 * {@link AccountsWebApplication}. This is a deliberate separation of concerns
 * and allows the application to run:
 * <ul>
 * <li>Standalone - by executing {@link AccountsWebApplication#main(String[])}</li>
 * <li>As a microservice - by executing {@link AccountsServer2#main(String[])}</li>
 * </ul>
 * 
 */
@EnableDiscoveryClient
@Import(AccountsWebApplication.class)
public class AccountsServer2 {

	protected Logger logger = Logger.getLogger(AccountsServer2.class.getName());

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for accounts-server.properties or
		// accounts-server.yml
		//System.setProperty("spring.cloud.config.name", "accounts2-server");

		SpringApplication.run(AccountsServer2.class, args);
	}
}
