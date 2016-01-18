package com.tii.microservices.kb;

import org.springframework.data.jpa.repository.JpaRepository;


public interface KnowledgeBaseRepository extends JpaRepository<KnowledgeBase, Long> {
    
    KnowledgeBase findByquestionText(String questionText);
    //KnowledgeBase findById(Long id);
   
}
