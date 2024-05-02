package org.revhire.model;

public class Skill {
    private int id;
    private int jobSeekerId;
    private String skillName;
    private String proficiencyLevel;

    // Constructor, getters, and setters
    public Skill(int id, int jobSeekerId, String skillName, String proficiencyLevel) {
        this.id = id;
        this.jobSeekerId = jobSeekerId;
        this.skillName = skillName;
        this.proficiencyLevel = proficiencyLevel;
    }

    // Getters and setters...


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJobSeekerId() {
        return jobSeekerId;
    }

    public void setJobSeekerId(int jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getProficiencyLevel() {
        return proficiencyLevel;
    }

    public void setProficiencyLevel(String proficiencyLevel) {
        this.proficiencyLevel = proficiencyLevel;
    }
}

