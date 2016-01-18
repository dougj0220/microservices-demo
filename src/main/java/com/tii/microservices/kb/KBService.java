package com.tii.microservices.kb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class KBService {
    
    private final KnowledgeBaseRepository knowledgeBaseRepository;
    
    @Autowired
    public KBService(KnowledgeBaseRepository knowledgeBaseRepository) {
        this.knowledgeBaseRepository = knowledgeBaseRepository;
    }
    
    public KnowledgeBase create(KnowledgeBase kb) {
        return knowledgeBaseRepository.save(kb);
    }
    
    public KnowledgeBase getById(Long id) throws Exception {
        return knowledgeBaseRepository.findOne(id);
    }
    
    public KnowledgeBase getByQuestion(String question) throws Exception {
        return knowledgeBaseRepository.findByquestionText(question);
    }
    
    public KnowledgeBase update(KnowledgeBase kb) {
        return knowledgeBaseRepository.save(kb);
    }
    
    @Cacheable(value="list", key="#id")
    public List<String> getList(String id) {
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        list.add("cool");
        
        return list;
    }
        
    public String getFooBar() throws Exception {
        Thread.sleep(10000);
        return "foo-bar!!";
    }
    
    public String getBlah() throws Exception {
        Thread.sleep(10000);
        return "blah!!";
    }

}
