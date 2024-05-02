package org.revhire.model;

import java.sql.Date;

public class JobAppication {
    int appicationid;
    int jobId;
    int jobSeekerId;
    Date registrationDate;
    String applicationStatus;

    public JobAppication(){

    }

    public JobAppication(int appicationid, int jobId,int jobSeekerId, Date registrationDate, String applicationStatus) {
        this.appicationid = appicationid;
        this.jobSeekerId=jobSeekerId;
        this.jobSeekerId = jobSeekerId;
        this.registrationDate = registrationDate;
        this.applicationStatus = applicationStatus;
    }

    public int getAppicationid() {
        return appicationid;
    }

    public void setAppicationid(int appicationid) {
        this.appicationid = appicationid;
    }

    public int getJobSeekerId() {
        return jobSeekerId;
    }

    public void setJobSeekerId(int jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }


    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    @Override
    public String toString() {
        return "JobAppication{" +
                "appicationid=" + appicationid +
                ", jobId=" + jobId +
                ", jobSeekerId=" + jobSeekerId +
                ", registrationDate=" + registrationDate +
                ", applicationStatus='" + applicationStatus + '\'' +
                '}';
    }
}
