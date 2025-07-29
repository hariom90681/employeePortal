package com.vw.employeeportal.binding;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnquiryForm {

    private String employeeName;

    private Long employeePhNo;

    private String classMode;

    private String courseName;

    private String enqStatus;

}
