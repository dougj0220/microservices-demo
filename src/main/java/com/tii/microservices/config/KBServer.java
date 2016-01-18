package com.tii.microservices.config;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@EnableDiscoveryClient
@Import(KBWebApplication.class)
public class KBServer {
	
	/*@Autowired
	void setEnvironment(Environment e) {
		System.out.println("Found hello property: " + e.getProperty("hello"));
	}*/
	
    public static void main(String[] args) {
        //ConfigurableApplicationContext context = 
        SpringApplication.run(KBServer.class, args);
    }
}
