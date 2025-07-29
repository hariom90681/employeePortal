package com.vw.employeeportal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vw_enquiry_status")
public class EnquiryStatus {

    @Id
    private Integer statusId;
    private String statusName;
}
