package com.vw.employeeportal.repository;

import com.vw.employeeportal.entity.EnquiryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnquiryStatusRepo extends JpaRepository<EnquiryStatus, Integer> {
}
