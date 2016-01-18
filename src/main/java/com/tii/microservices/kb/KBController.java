package com.tii.microservices.kb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("/kb")
public class KBController {
    
    protected KBService knowledgeBaseService;
    
    public String oldText;
    
	@Value("${hello}")
	private String hello;
    
    @Autowired
    public KBController(KBService knowledgeBaseService) {
       this.knowledgeBaseService = knowledgeBaseService; 
    }
    
    @RequestMapping("/hello")
    public String index() {      
    	return hello;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public KnowledgeBase getById(@PathVariable Long id) throws Exception {

        return knowledgeBaseService.getById(id);
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<String> getLists() throws Exception {

        return knowledgeBaseService.getList("99");
    }
    
    @RequestMapping(value = "/update/{id}/{text}", method = RequestMethod.GET)   
    @Caching(evict={@CacheEvict(value="question", key="#root.target.oldText")}, put={@CachePut(value="question", key="#text", unless="#result == null")})
    public String update(@PathVariable Long id, @PathVariable String text) throws Exception {
        KnowledgeBase kb = knowledgeBaseService.getById(id);
        oldText = kb.getQuestionText();
        kb.setQuestionText(text);
        knowledgeBaseService.update(kb);
               
        return kb.getResponseText();
    }
    
    @RequestMapping(value = "/question/{text}", method = RequestMethod.GET)
    @Cacheable(value="question", unless="#result == null")
    public String getByQuestion(@PathVariable String text) throws Exception {
       KnowledgeBase kb = knowledgeBaseService.getByQuestion(text);
       String ret = null;
       
       if (kb != null) {
           ret = kb.getResponseText();
       }

        return ret;
    }
    
    @RequestMapping("/foo/{id}")   
    @Cacheable("foo")
    public String foo(@PathVariable String id)  throws Exception {
        return knowledgeBaseService.getFooBar();
    }
    
    @RequestMapping("/blah/{id}")
    @Cacheable(value="blah")
    public String bar(@PathVariable String id)  throws Exception {
        return knowledgeBaseService.getBlah();
    }
}
