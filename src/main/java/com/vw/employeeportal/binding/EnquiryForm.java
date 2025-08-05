package com.vw.employeeportal.binding;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnquiryForm {

    private String employeeName;

    private Long employeePhone;

    private String enquiryType;    // e.g. HR, IT, Operations (was "classMode")

    private String enquiryTopic;   // e.g. Payroll, System Access (was "courseName")

    private String enqStatus;      // e.g. NEW, ENROLLED, LOST

}
