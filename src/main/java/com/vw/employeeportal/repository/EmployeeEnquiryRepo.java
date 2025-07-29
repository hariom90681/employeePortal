package com.vw.employeeportal.repository;

import com.vw.employeeportal.entity.EmployeeEnquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeEnquiryRepo extends JpaRepository<EmployeeEnquiry, Integer> {
}
