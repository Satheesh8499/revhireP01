package org.revhire.model;

public class Job {
    private int id;
    private int employerId;
    private String title;
    private String description;
    private String location;
    private double salary;
    private int experience;
    public Job(int id, int employerId, String title, String description, String location, double salary,int experience) {
        this.id = id;
        this.employerId = employerId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.salary = salary;
        this.experience=experience;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployerId() {
        return employerId;
    }

    public void setEmployerId(int employerId) {
        this.employerId = employerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", employerId=" + employerId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", salary=" + salary +
                ", experience=" + experience +
                '}';
    }
}

