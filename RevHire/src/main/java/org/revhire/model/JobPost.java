package org.revhire.model;

public class JobPost extends Job{
    String company;
    String contact;
    String email;
    String address;


    public JobPost(int id, int employerId, String company,String description, String contact, String email, String address, String title,String location, double salary, int experience) {
        super(id, employerId, title, description, location, salary, experience);
          this.company=company;
          this.contact=contact;
          this.email=email;
          this.address=address;

    }



    public String getCompany() {
        return company;
    }

    public void setCompany(String campany) {
        this.company = campany;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String cantact) {
        this.contact = cantact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "JobPost{" +
                "company='" + company + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
