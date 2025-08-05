package com.vw.employeeportal.binding;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponse {

    private Integer totalEnquiriesCnt;
    private Integer enrolledCnt;   // or Resolved
    private Integer lostCnt;
    private Integer pendingCnt;

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

    public Integer getPendingCnt() {
        return pendingCnt;
    }

    public void setPendingCnt(Integer pendingCnt) {
        this.pendingCnt = pendingCnt;
    }
}
