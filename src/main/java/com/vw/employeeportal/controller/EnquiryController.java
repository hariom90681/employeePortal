package com.vw.employeeportal.controller;

import com.vw.employeeportal.binding.DashboardResponse;
import com.vw.employeeportal.binding.EnquiryForm;
import com.vw.employeeportal.service.EnquiryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
public class EnquiryController {

    @Autowired
    private EnquiryService enquiryService;

    // It's better practice to pass session as a method parameter where needed
    // instead of autowiring it as a field.

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/dashboard")
    public String dashboardPage(Model model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/"; // Redirect to login if user is not in session
        }
        DashboardResponse dashboardData = enquiryService.getDashboardData(userId);
        model.addAttribute("dashboardData", dashboardData);
        return "dashboard";
    }

    @GetMapping("/addEnquiry")
    public String addEnquiryPage(Model model) {
        // This part now only needs to set up the initial form and status dropdown.
        // The topics dropdown will be handled dynamically.
        List<String> enquiryStatuses = enquiryService.getEnquiryStatuses();
        model.addAttribute("enquiryForm", new EnquiryForm());
        model.addAttribute("statusList", enquiryStatuses);
        return "add-employee-enquiry";
    }

    @PostMapping("/addEnquiry")
    public String handleAddEnquiry(@ModelAttribute("enquiryForm") EnquiryForm form,
                                   HttpSession session,
                                   RedirectAttributes redirectAttributes) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/"; // Redirect to login if user is not in session
        }
        boolean isSaved = enquiryService.saveEnquiry(form, userId);
        if (isSaved) {
            redirectAttributes.addFlashAttribute("successMsg", "Enquiry saved successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMsg", "Failed to save enquiry.");
        }
        return "redirect:/dashboard";
    }

    @GetMapping("/enquiries")
    public String viewEnquiriesPage(){
        return "view-enquiries";
    }

    // ===================================================================
    //  ✅ NEW METHOD ADDED TO SUPPORT DYNAMIC DROPDOWNS
    // ===================================================================
    @GetMapping("/getTopics")
    @ResponseBody
    public List<String> getTopics(@RequestParam String type) {
        // This logic provides the topics based on the selected type.
        // You can fetch this from a database in the future.
        Map<String, List<String>> topicMap = Map.of(
                "HR", List.of("Payroll", "Leave Policy", "Benefits"),
                "IT", List.of("Hardware Issue", "Software Request", "Password Reset"),
                "Training", List.of("New Course Request", "Schedule Inquiry"),
                "Operations", List.of("Logistics", "Supply Chain", "Facility Management")
        );
        return topicMap.getOrDefault(type, List.of());
    }
}