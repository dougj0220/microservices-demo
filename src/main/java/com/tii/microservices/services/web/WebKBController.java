package com.tii.microservices.services.web;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tii.microservices.kb.KnowledgeBase;

/**
 * Client controller, fetches KB info from the micro service via
 * {@link WebKBService}.
 * 
 */
@Controller
public class WebKBController {

	@Autowired
	protected WebKBService kbService;
	


	protected Logger logger = Logger.getLogger(WebKBController.class
			.getName());

	public WebKBController(WebKBService kbService) {
		this.kbService = kbService;
	}

	/*@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("question", "response");
	}*/
	
	@RequestMapping("/hello")
	public String hello(Model model) {
		String hello =  kbService.getHelloProp();
		logger.info("kbService is returning hello property value: " + hello);
		model.addAttribute("hello", hello);
		
		return "hello";
	}

	@RequestMapping("/kb-web")
	public String goHome() {
		return "index";
	}

	@RequestMapping("/kb/{id}")
	public String byId(Model model,
			@PathVariable("id") Long id) {

		logger.info("web-service byId() invoked: " + id);

		KnowledgeBase kb = kbService.findById(id);
		logger.info("web-service byId() found: " + kb.getId());
		model.addAttribute("kb", kb);
		
		return "kb/kb";
	}
	
	@RequestMapping(value = "/kb/update/{id}/{text}")
	public String update(Model model,@PathVariable Long id, @PathVariable String text) {
		logger.info("web-service update() invoked for id: " + id);

		String response = kbService.update(id, text);
		model.addAttribute("response", response);
		
		return null;
	}

	@RequestMapping("/kb/question/{text}")
	public String getByQuestion(Model model, @PathVariable("text") String text) {
		logger.info("web-service getByQuestion() invoked: " + text);

		String response = kbService.getByQuestion(text);
		logger.info("web-service getByQuestion() found: " + response);
		
		model.addAttribute("question", text);
		if (response != null)
			model.addAttribute("response", response);
		
		return "getByQuestion";
	}

	@RequestMapping(value = "/kb/list", method = RequestMethod.GET)
	public List<String> getLists(Model model) {
		
		return kbService.getLists();
	}
}
