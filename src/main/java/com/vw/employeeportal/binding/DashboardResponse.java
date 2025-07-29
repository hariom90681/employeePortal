package com.vw.employeeportal.binding;

import lombok.Data;

@Data
public class DashboardResponse {

    private Integer totalEnquiriesCnt;

    private Integer enrolledCnt;

    private Integer lostCnt;

    // Getters and Setters

    public Integer getTotalEnquiriesCnt() {
        return totalEnquiriesCnt;
    }

    public void setTotalEnquiriesCnt(Integer totalEnquiriesCnt) {
        this.totalEnquiriesCnt = totalEnquiriesCnt;
    }

    public Integer getEnrolledCnt() {
        return enrolledCnt;
    }

    public void setEnrolledCnt(Integer enrolledCnt) {
        this.enrolledCnt = enrolledCnt;
    }

    public Integer getLostCnt() {
        return lostCnt;
    }

    public void setLostCnt(Integer lostCnt) {
        this.lostCnt = lostCnt;
    }
}
