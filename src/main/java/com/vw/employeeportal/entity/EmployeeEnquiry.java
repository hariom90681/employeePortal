package com.vw.employeeportal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vw_employee_enquiries")
public class EmployeeEnquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer enquiryId;

    private String employeeName;

    private Long employeePhone;

    private String enqStatus;

    private String enquiryType;   // Replacing "classMode" (e.g. HR, IT, Training)

    private String enquiryTopic;  // Replacing "courseName" (e.g. Payroll, System Access)

    @CreationTimestamp
    private LocalDate dateCreated;
    @CreationTimestamp
    private LocalDate dateUpdated;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetails user; // enquiries are belongs to which user

}
