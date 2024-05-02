package org.revhire.dao;

import org.revhire.model.Experience;
import org.revhire.util.ConnectionUtil;
import org.revhire.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExperienceDAO {
    // Add experience to a job seeker's profile
    public void addExperience(Experience experience) throws SQLException {
        String query = "INSERT INTO experience (job_seeker_id, job_title, company_name, location, start_date, end_date, responsibilities) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
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

    // Retrieve experiences for a job seeker
    public List<Experience> getExperiencesByJobSeekerId(int jobSeekerId) throws SQLException {
        List<Experience> experiences = new ArrayList<>();
        String query = "SELECT * FROM experience WHERE job_seeker_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
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
}

