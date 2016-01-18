package com.tii.microservices.kb;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nlplog")
public class KnowledgeBase {

    @Id
    @Column(name = "nl_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cmn_id")
    private Long companyId;

    @Column(name = "c_id", columnDefinition = "longtext")
    private String campaignId;

    @Column(name = "type")
    private String questionType;

    @Column(name = "question", columnDefinition = "longtext")
    private String questionText;

    @Column(name = "matchedQuestions", columnDefinition = "longtext")
    private String matchedQuestionText;

    @Column(name = "responseId")
    private Long responseId;

    @Column(name = "responseText", columnDefinition = "longtext")
    private String responseText;

    @Column(name = "responseMedia", columnDefinition = "longtext")
    private String responseMedia;

    @Column(name = "insertionTime")
    private Long insertionTime;

    @Column(name = "assignedQuestionId")
    private Long assignedQuestionId;
    
    public KnowledgeBase() {
    	// empty ctor
    }

    //----------------------g/s--------------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCampaignId() {
        return new Long(campaignId);
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId.toString();
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Long getResponseId() {
        return responseId;
    }

    public void setResponseId(Long responseId) {
        this.responseId = responseId;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public String getResponseMedia() {
        return responseMedia;
    }

    public void setResponseMedia(String responseMedia) {
        this.responseMedia = responseMedia;
    }

    public Long getInsertionTime() {
        return insertionTime;
    }

    public void setInsertionTime(Long insertionTime) {
        this.insertionTime = insertionTime;
    }

    public String getMatchedQuestionText() {
        return matchedQuestionText;
    }

    public void setMatchedQuestionText(String matchedQuestionText) {
        this.matchedQuestionText = matchedQuestionText;
    }

    public Long getAssignedQuestionId() {
        return assignedQuestionId;
    }

    public void setAssignedQuestionId(Long assignedQuestionId) {
        this.assignedQuestionId = assignedQuestionId;
    }
}
