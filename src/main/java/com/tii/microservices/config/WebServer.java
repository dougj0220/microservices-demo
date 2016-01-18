package com.tii.microservices.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.tii.microservices.services.web.HomeController;
import com.tii.microservices.services.web.WebAccountsController;
import com.tii.microservices.services.web.WebAccountsService;
import com.tii.microservices.services.web.WebKBController;
import com.tii.microservices.services.web.WebKBService;

/**
 * Accounts web-server. Works as a microservice client, fetching data from the
 * Account-Service. Uses the Discovery Server (Eureka) to find the microservice.
 * 
 */
@SpringBootApplication
@EnableDiscoveryClient
// Disable component scanner ...
@ComponentScan(useDefaultFilters = false)
public class WebServer {

	/**
	 * URL uses the logical name of account-service - upper or lower case,
	 * doesn't matter.
	 */
	public static final String ACCOUNTS_SERVICE_URL = "http://ACCOUNTS-SERVICE";
	public static final String KB_SERVICE_URL = "http://KB-SERVICE";

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for web-server.properties or web-server.yml
		//System.setProperty("spring.config.name", "web-server");
		SpringApplication.run(WebServer.class, args);
	}

	/**
	 * The AccountService encapsulates the interaction with the micro-service.
	 * 
	 * @return A new service instance.
	 */
	@Bean
	public WebAccountsService accountsService() {
		return new WebAccountsService(ACCOUNTS_SERVICE_URL);
	}

	/**
	 * Create the controller, passing it the {@link WebAccountsService} to use.
	 * 
	 * @return
	 */
	@Bean
	public WebAccountsController accountsController() {
		return new WebAccountsController(accountsService());
	}
	
	
	/**
	 * The KBService encapsulates the interaction with the KB micro-service.
	 * 
	 * @return A new service instance.
	 */
	@Bean
	public WebKBService kbService() {
		return new WebKBService(KB_SERVICE_URL);
	}
	
	/**
	 * Create the KB controller, passing it the {@link WebKBService} to use.
	 * 
	 * @return
	 */
	@Bean
	public WebKBController kbController() {
		return new WebKBController(kbService());
	}

	@Bean
	public HomeController homeController() {
		return new HomeController();
	}
}
