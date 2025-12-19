package com.project.Project.model;


import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "programs")
public class Programs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long programid;

    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculties faculty;

    private String name;
    private String description;

    @Column(name = "created_at")
    private Timestamp createdAt;

    public Long getProgramid() {
        return programid;
    }

    public void setProgramid(Long programid) {
        this.programid = programid;
    }

    public Faculties getFaculty() { return faculty; }
    public void setFaculty(Faculties faculty) { this.faculty = faculty; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
