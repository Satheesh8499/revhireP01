package org.revhire.service;

import org.revhire.dao.EmployerDAO;
import org.revhire.dao.JobDAO;
import org.revhire.model.Employer;
import org.revhire.model.Job;

import java.sql.SQLException;
import java.util.List;

public class EmployerService {
    private EmployerDAO employerDAO = new EmployerDAO();
    private JobDAO jobDao=new JobDAO();
    // Register a new employer
    public void registerEmployer(Employer employer) throws SQLException {
        employerDAO.registerEmployer(employer);
    }

    // Retrieve an employer by username
    public Employer getEmployerByUsername(String username) throws SQLException {
        return employerDAO.getEmployerByUsername(username);
    }

    public void deletePost(int employerId, int jobId) {
          employerDAO.deletePost(employerId,jobId);
    }
    public void postJob(Job job){
        employerDAO.postJob(job);
    }
    public List<Job> allJobByEmployer(int id){
        return jobDao.viewAllJobsByEmployerById(id);
    }
    public void updateStatus(int empId,int applicationId,String applicationStatus){
        employerDAO.updateStatus(empId,applicationId,applicationStatus);
    }
}

