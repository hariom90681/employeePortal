package com.vw.employeeportal.service;

import com.vw.employeeportal.binding.DashboardResponse;
import com.vw.employeeportal.binding.EnquiryForm;
import com.vw.employeeportal.entity.EmployeeEnquiry;
import com.vw.employeeportal.entity.EnquiryStatus;
import com.vw.employeeportal.entity.EnquiryTopic;
import com.vw.employeeportal.entity.UserDetails;
import com.vw.employeeportal.repository.EmployeeEnquiryRepo;
import com.vw.employeeportal.repository.EnquiryStatusRepo;
import com.vw.employeeportal.repository.EnquiryTopicRepo;
import com.vw.employeeportal.repository.UserDetailsRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnquiryServiceImpl implements EnquiryService{

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    @Autowired
    private EnquiryTopicRepo enquiryTopicRepo;

    @Autowired
    private EmployeeEnquiryRepo employeeEnquiryRepo;

    @Autowired
    private EnquiryStatusRepo enquiryStatusRepo;

    @Override
    public DashboardResponse getDashboardData(Integer userId) {

        DashboardResponse response = new DashboardResponse();

        //based on the user Id i am getting the user record
      Optional<UserDetails> findById = userDetailsRepo.findById(userId);

      if (findById.isPresent()){
         UserDetails userEntity = findById.get();

        List<EmployeeEnquiry> enquiries = userEntity.getEnquiries();
        Integer totalCnt = enquiries.size();

        Integer enrolledCnt = enquiries.stream()
                .filter(e->e.getEnqStatus().equals("ENROLLED"))
                .collect(Collectors.toList()).size();

        Integer lostCnt = enquiries.stream()
                .filter(e-> e.getEnqStatus().equals("LOST"))
                .collect(Collectors.toList()).size();

        response.setTotalEnquiriesCnt(totalCnt);
        response.setEnrolledCnt(enrolledCnt);
        response.setLostCnt(lostCnt);

      }

        return response;
    }


    //These two methods are used for dropdown data
    @Override
    public List<String> getEnquiryTopics() {
       List<EnquiryTopic> findAll = enquiryTopicRepo.findAll();

       List<String> names = new ArrayList<>();
       for (EnquiryTopic entity : findAll){
           names.add(entity.getTopicName());
       }
        return names;
    }

    @Override
    public List<String> getEnquiryStatuses() {

      List<EnquiryStatus> findAll = enquiryStatusRepo.findAll();

      List<String> statusList = new ArrayList<>();

      for (EnquiryStatus entity : findAll){
          statusList.add(entity.getStatusName());
      }
        return statusList;
    }

    @Override
    public boolean saveEnquiry(EnquiryForm form, Integer userId) {
        EmployeeEnquiry enquiryEntity = new EmployeeEnquiry();

        // 2. Copy the data from the form object into the entity object
        BeanUtils.copyProperties(form, enquiryEntity);

        // 3. Find the user who is creating this enquiry
        Optional<UserDetails> userOpt = userDetailsRepo.findById(userId);

        if (userOpt.isPresent()) {
            UserDetails user = userOpt.get();
            // 4. Set the user on the enquiry entity to create the relationship
            enquiryEntity.setUser(user);
        } else {
            // Handle case where user is not found, maybe return false or throw an exception
            return false;
        }

        // 5. Save the new enquiry entity to the database
        employeeEnquiryRepo.save(enquiryEntity);

        return true;
    }

}
