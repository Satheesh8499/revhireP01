package org.revhire.dao;

import org.revhire.model.Employer;
import org.revhire.model.Job;
import org.revhire.util.DatabaseConnection;

import java.sql.*;

public class EmployerDAO {
    // Register a new employer
    public void registerEmployer(Employer employer) throws SQLException {
        String query = "INSERT INTO employers (employer_name,username, password, email, company_name, contact_number, address, company_description, industry) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1,employer.getName());
            statement.setString(2, employer.getUsername());
            statement.setString(3, employer.getPassword());
            statement.setString(4, employer.getEmail());
            statement.setString(5, employer.getCompanyName());
            statement.setString(6, employer.getContactNumber());
            statement.setString(7, employer.getAddress());
            statement.setString(8, employer.getCompanyDescription());
            statement.setString(9, employer.getIndustry());
            statement.executeUpdate();
        }
    }

    // Retrieve an employer by username
    public Employer getEmployerByUsername(String username) throws SQLException {
        String query = "SELECT * FROM employers WHERE username = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name=resultSet.getString("employer_name");
                    String password = resultSet.getString("password");
                    String email = resultSet.getString("email");
                    String companyName = resultSet.getString("company_name");
                    String contactNumber = resultSet.getString("contact_number");
                    String address = resultSet.getString("address");
                    String companyDescription = resultSet.getString("company_description");
                    String industry = resultSet.getString("industry");
                    return new Employer(id,name,username, password, email, companyName, contactNumber, address, companyDescription, industry);
                }
            }
        }
        return null; // Employer not found
    }

    public void postJob(Job job)  {
        String sql = "INSERT INTO jobs (employer_id, title, description, location, salary, experience) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql)) {

            statement.setInt(1, job.getEmployerId());
            statement.setString(2, job.getTitle());
            statement.setString(3, job.getDescription());
            statement.setString(4, job.getLocation());
            statement.setDouble(5, job.getSalary());
            statement.setInt(6, job.getExperience());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Job posted successfully.");
            } else {
                System.out.println("Failed to post job.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deletePost(int eid,int jobId){
        String DELETE_JOB_SQL = "DELETE FROM jobs WHERE  employer_id=? and id=?";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(DELETE_JOB_SQL)) {


            statement.setInt(1, eid);
             statement.setInt(2,jobId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Job post deleted successfully.");
            } else {
                System.out.println("No job post found with ID: " + jobId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStatus(int empId,int applicationId,String status){
        try {
            Connection connection=DatabaseConnection.getConnection();
            PreparedStatement p=connection.prepareStatement("update job_application set status=? where id=?");
              p.setString(1,status);
              p.setInt(2,applicationId);
             int n= p.executeUpdate();
            System.out.println(n);
        }
        catch (SQLException sql){
            System.out.println(sql);
        }
    }
//    public EmployerDetails  getById(int id){
//        String  query= "select employer_name,company_name,contact_number,address from emolyers where id = ?";
//      return null;
//    }


}

