package com.tii.microservices.config;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The kb web-application. This class has two uses:
 * <ol>
 * <li>Provide configuration and setup for {@link AccountsServer} ... or</li>
 * <li>Run as a stand-alone Spring Boot web-application for testing (in which
 * case there is <i>no</i> microservice registration</li>
 * </ol>
 * <p>
 * To execute as a microservice, run {@link AccountsServer} instead.
 * 
 */
@SpringBootApplication
@EntityScan("io.doug.microservices.kb")
@EnableJpaRepositories("io.doug.microservices.kb")
@ComponentScan(basePackages="io.doug.microservices.kb")
@PropertySource("classpath:mysql-config.properties")
public class KBWebApplication {

	protected Logger logger = Logger.getLogger(KBWebApplication.class
			.getName());

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		SpringApplication.run(KBWebApplication.class, args);
	}
}
