package org.revhire.dao;

import org.revhire.model.Skill;
import org.revhire.util.ConnectionUtil;
import org.revhire.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SkillDAO {
    // Add a skill to a job seeker's profile
    public void addSkill(Skill skill) throws SQLException {
        String query = "INSERT INTO job_seeker_skills (job_seeker_id, skill_name, proficiency_level) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, skill.getJobSeekerId());
            statement.setString(2, skill.getSkillName());
            statement.setString(3, skill.getProficiencyLevel());
            statement.executeUpdate();
        }
    }

    // Retrieve skills for a job seeker
    public List<Skill> getSkillsByJobSeekerId(int jobSeekerId) throws SQLException {
        List<Skill> skills = new ArrayList<>();
        String query = "SELECT * FROM job_seeker_skills WHERE job_seeker_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, jobSeekerId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String skillName = resultSet.getString("skill_name");
                    String proficiencyLevel = resultSet.getString("proficiency_level");
                    skills.add(new Skill(id, jobSeekerId, skillName, proficiencyLevel));
                }
            }
        }
        return skills;
    }
}

