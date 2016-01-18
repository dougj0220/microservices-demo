package com.tii.microservices.services.web;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tii.microservices.kb.KnowledgeBase;

/**
 * Hide the access to the microservice inside this local service.
 * 
 */
@Service
public class WebKBService {

	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;

	protected String serviceUrl;

	protected Logger logger = Logger.getLogger(WebKBService.class
			.getName());

	public WebKBService(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
				: "http://" + serviceUrl;
	}

	public KnowledgeBase findById(Long id) {
		logger.info("findById() invoked for id: " + id);
		return restTemplate.getForObject(serviceUrl + "/kb/{id}",
				KnowledgeBase.class, id);
	}
	
	public String getHelloProp() {
		logger.info("making rest call to get hello property from kb-service");
		return restTemplate.getForObject(serviceUrl + "/kb/hello", String.class); 
	}
	
	public List<String> getLists() {
		logger.info("getLists() invoked");
		
		return Arrays.asList(restTemplate.getForObject(serviceUrl + "/kb/list", String[].class));
	}
	
	public String update(Long id, String text) {
		logger.info("update() invoked: for " + id);
		
		return restTemplate.getForObject(serviceUrl + "/kb/update/{id}/{text}", String.class, id, text); 
	}
	
	public String getByQuestion(String text) {
		logger.info("getByQuestion() invoked for text: " + text);
		
		return restTemplate.getForObject(serviceUrl + "/kb/question/{text}", String.class, text);
	}
}
