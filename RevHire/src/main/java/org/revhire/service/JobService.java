package org.revhire.service;

import org.revhire.dao.JobDAO;
import org.revhire.model.Job;
import org.revhire.model.JobAppication;
import org.revhire.model.JobPost;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class JobService {
    private JobDAO jobDAO = new JobDAO();

    public void postJob(Job job) throws SQLException {
        jobDAO.postJob(job);
    }

    // Retrieve jobs based on filters
    public List<Job> searchJobs(String title, String location, int experience) throws SQLException {
        return jobDAO.searchJobs(title, location, experience);
    }
    public List<JobPost> getAllJobPost(){
        return jobDAO.getAllJobs();
    }
    public List<JobPost> searchByRoleAndExperience(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter experience : ");
        int exp= sc.nextInt();
        System.out.println("Enter Job role");
        String role=sc.next();
      return  getAllJobPost().stream().filter((n)->n.getExperience()==exp||n.getTitle().equals(role)).collect(Collectors.toList());
    }
    public  List<JobPost>  searchByCompanyOrLocation(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter comapny name : ");
        String companyName= sc.next();
        System.out.println("Enter location");
        String location=sc.next();
        return  getAllJobPost().stream().filter((n)->n.getCompany().equals(companyName)||n.getLocation().equals(location)).collect(Collectors.toList());
    }
   public List<JobAppication> getApplications(int empId){
       return jobDAO.getApplications(empId);
   }

}

