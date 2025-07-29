package com.vw.employeeportal.controller;

import com.vw.employeeportal.binding.DashboardResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnquiryController {

  /*  @GetMapping("/dashboard")
    public String dashboardPage(){
        return "dashboard";
    }*/
  @GetMapping("/dashboard")
  public String dashboardPage(Model model) {
      DashboardResponse resp = new DashboardResponse();
      resp.setTotalEnquiriesCnt(100);
      resp.setEnrolledCnt(40);
      resp.setLostCnt(10);

      model.addAttribute("dashboard", resp);
      return "dashboard";
  }

    @GetMapping("/enquiry")
    public String enquiryPage(){
        return "add-enquiry";
    }

    @GetMapping("/enquiries")
    public String viewEnquiriesPage(){
        return "view-enquiries";
    }

}
