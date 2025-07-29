package com.vw.employeeportal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "vw_employee_enquiries")
public class EmployeeEnquiry {

    @Id
    private Integer enquiryId;
    private String employeeName;
    private Long employeePhNo;
    private String classMode;

    private String courseName;
    private Date createdDate;
    private Date updatedDate;

    private Integer userId;
}
