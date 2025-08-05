package com.vw.employeeportal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vw_enquiry_status")
public class EnquiryStatus {

    @Id
    @GeneratedValue
    private Integer statusId;
    private String statusName;
}
