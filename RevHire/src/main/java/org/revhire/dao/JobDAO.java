package org.revhire.dao;

import org.revhire.model.Job;
import org.revhire.model.JobAppication;
import org.revhire.model.JobPost;
import org.revhire.util.ConnectionUtil;
import org.revhire.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class JobDAO {
    // Post a new job
    public void postJob(Job job) throws SQLException {
        String query = "INSERT INTO jobs (employer_id, title, description, location, salary) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, job.getEmployerId());
            statement.setString(2, job.getTitle());
            statement.setString(3, job.getDescription());
            statement.setString(4, job.getLocation());
            statement.setDouble(5, job.getSalary());
            statement.executeUpdate();
        }
    }

    // Retrieve jobs based on filters
    public List<Job> searchJobs(String title, String location, int experience) throws SQLException {
        List<Job> jobs = new ArrayList<>();
        String query = "SELECT * FROM jobs WHERE title LIKE ? AND location LIKE ? AND experience_required <= ?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + title + "%");
            statement.setString(2, "%" + location + "%");
            statement.setInt(3, experience);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int employerId = resultSet.getInt("employer_id");
                    String jobTitle = resultSet.getString("title");
                    String jobDescription = resultSet.getString("description");
                    String jobLocation = resultSet.getString("location");
                    double salary = resultSet.getDouble("salary");
                    int exp=resultSet.getInt("experience");
                    jobs.add(new Job(id, employerId, jobTitle, jobDescription, jobLocation,salary,experience));
                }
            }
        }
        return jobs;
    }

    public void postJob() {

    }


    public List<JobPost> getAllJobs() {
               String query="select j.*,e.company_name,e.contact_number,e.email,e.address from employers e join jobs j on e.id=j.employer_id";
               try {
                   Connection connection = DatabaseConnection.getConnection();
                   Statement statement=connection.createStatement();
                   ResultSet resultSet = statement.executeQuery(query);
                   List<JobPost> jobPosts=new ArrayList<>();
                   while(resultSet.next()){
                       int id = resultSet.getInt("id");
                       int employerId = resultSet.getInt("employer_id");
                       String title = resultSet.getString("title");
                       String description = resultSet.getString("description");
                       String location = resultSet.getString("location");
                       int experience = resultSet.getInt("experience");
                       String company=resultSet.getString("company_name");
                       String contact=resultSet.getString("contact_number");
                       String  email=resultSet.getString("email");
                       String address=resultSet.getString("address");
                       Double salary1=resultSet.getDouble("salary");
                       System.out.println(id);
                       JobPost j= new JobPost(id,employerId,company,description,contact,email,address,title,location, salary1, experience);
                       System.out.println(j);
                       jobPosts.add(j);

                   }
                   return jobPosts;
               }
              catch (SQLException e) {
                   throw new RuntimeException(e);
               }

    }

    public Job getJobById(int id){
        String sql = "SELECT id, employer_id, title, description, location, salary, experience FROM jobs WHERE id = ?";
        try  {
            Connection c = DatabaseConnection.getConnection();
            PreparedStatement statement = c.prepareStatement("SELECT id, employer_id, title, description, location, salary, experience FROM jobs WHERE id = ?");
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                int id1 = resultSet.getInt("id");
                int employerId = resultSet.getInt("employer_id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String location = resultSet.getString("location");
                double salary = resultSet.getDouble("salary");
                int experience = resultSet.getInt("experience");

                // Create and return the Job object
                return new Job(id1, employerId, title, description, location, salary, experience);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Job> viewAllJobsByEmployerById(int empId) {
        List<Job> jobs = new ArrayList<>();
        try{

            Connection c = DatabaseConnection.getConnection();
            Statement statement = c.createStatement();


            String sql = "select * from jobs where employer_id = "+empId;
            ResultSet resultSet = statement.executeQuery(sql);


            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                int employerId = resultSet.getInt("employer_id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String location = resultSet.getString("location");
                double salary = resultSet.getDouble("salary");
                int experience = resultSet.getInt("experience");


                Job job = new Job(id,employerId,title,description,location,salary,experience);
                jobs.add(job);

            }

        }
        catch (Exception e){
            System.out.println("sql exception");
            System.out.println(e.getMessage());
        }
        return jobs;

    }

    public List<JobAppication> getApplications(int empId){
        List<JobAppication> jobApplications = new ArrayList<>();
        try{

            Connection c = DatabaseConnection.getConnection();
            Statement statement = c.createStatement();


            String sql = "select ja.* from jobs  j  join job_application ja on j.id=ja.job_id  where j.employer_id="+empId;
            ResultSet resultSet = statement.executeQuery(sql);


            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                 int jobSeekerId=resultSet.getInt("job_seeker_id");
                 int jobId=resultSet.getInt("job_id");
                 Date date=resultSet.getDate("application_date");
                 String status=resultSet.getString("status");
              JobAppication j=new JobAppication();
              j.setAppicationid(id);
              j.setJobId(jobId);
              j.setJobSeekerId(jobSeekerId);
              j.setRegistrationDate(date);
              j.setApplicationStatus(status);
            jobApplications.add(j);


            }
            return jobApplications;

        }
        catch (Exception e){
            System.out.println("sql exception");
            System.out.println(e.getMessage());
        }
        return jobApplications;
    }

}

