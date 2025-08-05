package com.vw.employeeportal.service;


import com.vw.employeeportal.binding.DashboardResponse;
import com.vw.employeeportal.binding.EnquiryForm;

import java.util.List;

public interface EnquiryService {

    public DashboardResponse getDashboardData(Integer userId);

    /**
     * Retrieves the list of available enquiry topics (e.g. Payroll, System Access, etc.)
     * for use in the dropdown when adding a new employee enquiry.
     */
    public List<String> getEnquiryTopics();

    /**
     * Retrieves the list of available enquiry statuses
     * (e.g. NEW, ENROLLED, LOST) for use in the enquiry status dropdown.
     */
    public List<String> getEnquiryStatuses();

    public boolean saveEnquiry(EnquiryForm form, Integer userId);


}
