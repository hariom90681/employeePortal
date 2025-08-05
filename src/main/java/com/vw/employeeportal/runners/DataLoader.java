package com.vw.employeeportal.runners;

import com.vw.employeeportal.entity.EnquiryStatus;
import com.vw.employeeportal.entity.EnquiryTopic;
import com.vw.employeeportal.repository.EnquiryStatusRepo;
import com.vw.employeeportal.repository.EnquiryTopicRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private EnquiryTopicRepo enquiryTopicRepo;

    @Autowired
    private EnquiryStatusRepo enquiryStatusRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        enquiryTopicRepo.deleteAll();

        // Create an empty object, then set the name
        EnquiryTopic t1 = new EnquiryTopic();
        t1.setTopicName("Payroll");

        EnquiryTopic t2 = new EnquiryTopic();
        t2.setTopicName("System Access");

        EnquiryTopic t3 = new EnquiryTopic();
        t3.setTopicName("Benefits");

        enquiryTopicRepo.saveAll(Arrays.asList(t1, t2, t3));

        enquiryStatusRepo.deleteAll();

        EnquiryStatus s1 = new EnquiryStatus();
        s1.setStatusName("New");

        EnquiryStatus s2 = new EnquiryStatus();
        s2.setStatusName("In-Progress");

        EnquiryStatus s3 = new EnquiryStatus();
        s3.setStatusName("Closed");

        enquiryStatusRepo.saveAll(Arrays.asList(s1, s2, s3));


    }
}
