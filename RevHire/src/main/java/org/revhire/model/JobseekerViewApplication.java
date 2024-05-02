package org.revhire.model;

import java.sql.Date;

public class JobseekerViewApplication {
    int jobId;
    int applicationId;
    String companyName;
    String role;
    String location;
    Date registerDate;
    String experience;
    String applicationStatus;

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "JobseekerViewApplication{" +
                "jobId=" + jobId +
                ", applicationId=" + applicationId +
                ", companyName='" + companyName + '\'' +
                ", role='" + role + '\'' +
                ", location='" + location + '\'' +
                ", registerDate=" + registerDate +
                ", experience='" + experience + '\'' +
                ", applicationStatus='" + applicationStatus + '\'' +
                '}';
    }
}
