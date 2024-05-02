package org.revhire.model;

public class Employer {
    private int id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String companyName;
    private String contactNumber;
    private String address;
    private String companyDescription;
    private String industry;

    // Constructor, getters, and setters
    public Employer(int id, String name,String username, String password, String email, String companyName, String contactNumber, String address, String companyDescription, String industry) {
        this.id = id;
        this.name=name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.companyName = companyName;
        this.contactNumber = contactNumber;
        this.address = address;
        this.companyDescription = companyDescription;
        this.industry = industry;
    }

    // Getters and setters...


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

