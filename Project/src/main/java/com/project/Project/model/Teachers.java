package com.project.Project.model;


import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name = "teachers")
public class Teachers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherid;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    private String name;
    private String employee_id;
    private String email;
    private String qualifications;

    private String permanent_address;
    private String temporary_address;

    @JsonFormat(pattern="yyyy-MM-dd") // Forces the date to parse correctly from Postman
    private Date dob;
    private String contact_no;
    private Boolean status;
    private String genders;

    @Column(name = "created_at")
    private Timestamp createdAt;

    public Long getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Long teacherid) {
        this.teacherid = teacherid;
    }

    public Users getUser() { return user; }
    public void setUser(Users user) { this.user = user; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmployee_id() { return employee_id; }
    public void setEmployee_id(String employee_id) { this.employee_id = employee_id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getQualifications() { return qualifications; }
    public void setQualifications(String qualifications) { this.qualifications = qualifications; }

    public String getPermanent_address() { return permanent_address; }
    public void setPermanent_address(String permanent_address) {
        this.permanent_address = permanent_address;
    }

    public String getTemporary_address() { return temporary_address; }
    public void setTemporary_address(String temporary_address) {
        this.temporary_address = temporary_address;
    }

    public Date getDob() { return dob; }
    public void setDob(Date dob) { this.dob = dob; }

    public String getContact_no() { return contact_no; }
    public void setContact_no(String contact_no) { this.contact_no = contact_no; }

    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }

    public String getGenders() { return genders; }
    public void setGenders(String genders) { this.genders = genders; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
