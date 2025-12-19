package com.project.Project.model;


import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "students")
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentid;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    private String name;
    private String gender;
    private Date dob;

    private String permanent_address;
    private String temporary_address;

    private Long roll_no;
    private String batch;
    private Integer semester;
    private String faculty;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Programs program;

    @Column(name = "created_at")
    private Timestamp createdAt;

    public Long getStudentid() {
        return studentid;
    }

    public void setStudentid(Long studentid) {
        this.studentid = studentid;
    }

    public Users getUser() { return user; }
    public void setUser(Users user) { this.user = user; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public Date getDob() { return dob; }
    public void setDob(Date dob) { this.dob = dob; }

    public String getPermanent_address() { return permanent_address; }
    public void setPermanent_address(String permanent_address) {
        this.permanent_address = permanent_address;
    }

    public String getTemporary_address() { return temporary_address; }
    public void setTemporary_address(String temporary_address) {
        this.temporary_address = temporary_address;
    }

    public Long getRoll_no() { return roll_no; }
    public void setRoll_no(Long roll_no) { this.roll_no = roll_no; }

    public String getBatch() { return batch; }
    public void setBatch(String batch) { this.batch = batch; }

    public Integer getSemester() { return semester; }
    public void setSemester(Integer semester) { this.semester = semester; }

    public Programs getProgram() { return program; }
    public void setProgram(Programs program) { this.program = program; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}
