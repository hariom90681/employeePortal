package com.vw.employeeportal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vw_courses")
public class Course {

    @Id
    private Integer courseId;
    private String courseName;
}
