package com.vw.employeeportal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vw_enquiry_topics")
public class EnquiryTopic {

    @Id
    @GeneratedValue
    private Integer id;

    private String topicName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}
