package org.revhire.dao;

import org.revhire.model.*;
import org.revhire.util.ConnectionUtil;
import org.revhire.util.DatabaseConnection;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobSeekerDAO {
    // Register a new job seeker
    public void registerJobSeeker(JobSeeker jobSeeker) throws SQLException {
        String query = "INSERT INTO job_seekers (username, password, email, first_name, last_name, contact_number, address) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, jobSeeker.getUsername());
            statement.setString(2, jobSeeker.getPassword());
            statement.setString(3, jobSeeker.getEmail());
            statement.setString(4, jobSeeker.getFirstName());
            statement.setString(5, jobSeeker.getLastName());
            statement.setString(6, jobSeeker.getContactNumber());
            statement.setString(7, jobSeeker.getAddress());
            statement.executeUpdate();
        }
    }
    public void addResume(int jobSeekerId, String resume) throws SQLException {
        String query = "UPDATE job_seekers SET resume = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, resume);
            statement.setInt(2, jobSeekerId);
            statement.executeUpdate();
        }
    }
    public JobSeeker getJobSeekerByUsername(String username) throws SQLException {
        String query = "SELECT * FROM job_seekers WHERE username = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                System.out.println("executed  login "+resultSet);
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String password = resultSet.getString("password");
                    String email = resultSet.getString("email");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String contactNumber = resultSet.getString("contact_number");
                    String address = resultSet.getString("address");
                    String resume = resultSet.getString("resume");
                    return new JobSeeker(id, username, password, email, firstName, lastName, contactNumber, address, resume);
                }
            }
        }
        return null;
    }

    public void addExperience(Experience experience) throws SQLException {
        String query = "INSERT INTO experience (job_seeker_id, job_title, company_name, location, start_date, end_date, responsibilities) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, experience.getJobSeekerId());
            statement.setString(2, experience.getJobTitle());
            statement.setString(3, experience.getCompanyName());
            statement.setString(4, experience.getLocation());
            statement.setDate(5, experience.getStartDate());
            statement.setDate(6, experience.getEndDate());
            statement.setString(7, experience.getResponsibilities());
            statement.executeUpdate();
        }
    }

    public List<Experience> getExperiencesByJobSeekerId(int jobSeekerId) throws SQLException {
        List<Experience> experiences = new ArrayList<>();
        String query = "SELECT * FROM experience WHERE job_seeker_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, jobSeekerId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String jobTitle = resultSet.getString("job_title");
                    String companyName = resultSet.getString("company_name");
                    String location = resultSet.getString("location");
                    Date startDate = resultSet.getDate("start_date");
                    Date endDate = resultSet.getDate("end_date");
                    String responsibilities = resultSet.getString("responsibilities");
                    experiences.add(new Experience(id, jobSeekerId, jobTitle, companyName, location, startDate, endDate, responsibilities));
                }
            }
        }
        return experiences;
    }

    public List<Job> allJobs(){
        List<Job> jobs = new ArrayList<>();

        String sql = "SELECT id, employer_id, title, description, location, salary, experience FROM jobs";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int employerId = resultSet.getInt("employer_id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String location = resultSet.getString("location");
                double salary = resultSet.getDouble("salary");
                int experience = resultSet.getInt("experience");
                Job job = new Job(id, employerId, title, description, location, salary, experience);
                jobs.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }
    public void jobApply(JobAppication jobAppication){
        try {
            Connection connection=DatabaseConnection.getConnection();
            PreparedStatement statement1 = connection.prepareStatement("select * from job_application where job_seeker_id= ? and job_id=?");
               statement1.setInt(1,jobAppication.getJobSeekerId());
               statement1.setInt(2,jobAppication.getJobId());
               ResultSet num=statement1.executeQuery();
            if(!num.next()) {
                PreparedStatement statement = connection.prepareStatement("insert into  job_application (job_seeker_id,job_id,application_date) values (?,?,CONVERT(DATE, GETDATE()))");
                statement.setInt(1, jobAppication.getJobSeekerId());
                statement.setInt(2, jobAppication.getJobId());
                int n = statement.executeUpdate();
                System.out.println(n + " rows are affected");
            }
            else{
                System.out.println("already appied");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void withdrawApllication(JobAppication jobAppication){
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("delete from job_application where job_id=? and job_seeker_id=?");
              statement.setInt(1,jobAppication.getJobId());
              statement.setInt(2,jobAppication.getJobSeekerId());
            int res = statement.executeUpdate();
            if(res>=0){
                System.out.println("deleled.......");
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    public void forgotPassword(String username,String email,String password){
        try {
            Connection c = DatabaseConnection.getConnection();
            PreparedStatement statement = c.prepareStatement("select * from job_seekers where username=? and email=?");
                 statement.setString(1,username);
                 statement.setString(2,email);
            boolean execute = statement.execute();
            if(execute){
                     statement=c.prepareStatement("update job_seekers set password=? where username=?");
                     statement.setString(1,password);
                     statement.setString(2,username);
                     statement.execute();
                System.out.println("password updated successfully");
            }
        }
        catch(SQLException sqle){

        }
    }
    public List<JobseekerViewApplication> applicationsByJobSeekerId(int jobSeekerId){
      String sql="select n.*,e.company_name,e.email from (select app.*,j.title,j.employer_id,j.location,j.salary,j.experience from job_application app join jobs j on app.job_id=j.id where job_seeker_id=?)as n join employers e on n.employer_id=e.id";
      List<JobseekerViewApplication> jb=new ArrayList<>();
      try {
          Connection c = DatabaseConnection.getConnection();
          PreparedStatement statement = c.prepareStatement(sql);
           statement.setInt(1,jobSeekerId);

          ResultSet resultSet = statement.executeQuery();
          while(resultSet.next()){

                    int applicationId= resultSet.getInt("id");

                    int jobId=resultSet.getInt("job_id");

                     String companyName1=resultSet.getString("company_name");

                     String role=resultSet.getString("title");

                     String location=resultSet.getString("location");

                     String  email=resultSet.getString("email");
                    Date d=resultSet.getDate("application_date");

                    Double salary=resultSet.getDouble("salary");

                    String experience=resultSet.getString("experience");

                 String status=resultSet.getString("status");

                 JobseekerViewApplication va=new JobseekerViewApplication();
                 va.setApplicationId(applicationId);
                 va.setJobId(jobId);
                 va.setCompanyName(companyName1);
                 va.setLocation(location);
                 va.setRegisterDate(d);
                 va.setRole(role);
                 va.setExperience(experience);
                 va.setApplicationStatus(status);
                 jb.add(va);
          }
         return jb;
      }
      catch (SQLException s){
          System.out.println(s);
      }
        return jb;
    }
}
