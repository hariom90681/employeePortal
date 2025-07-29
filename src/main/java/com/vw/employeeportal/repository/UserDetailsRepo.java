package com.vw.employeeportal.repository;

import com.vw.employeeportal.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails, Integer> {


    UserDetails findByEmail(String email); // bcz we need to check that user email is already exist
}
