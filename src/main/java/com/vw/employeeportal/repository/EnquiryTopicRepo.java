package com.vw.employeeportal.repository;


import com.vw.employeeportal.entity.EnquiryTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnquiryTopicRepo extends JpaRepository<EnquiryTopic,Integer> {

}
