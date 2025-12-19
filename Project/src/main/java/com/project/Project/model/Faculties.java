package com.project.Project.model;


import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "faculties")
public class Faculties {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facultiesid;

    private String name;
    private String description;

    @Column(name = "created_at")
    private Timestamp createdAt;

    public Long getFacultiesid() {
        return facultiesid;
    }

    public void setFacultiesid(Long facultiesid) {
        this.facultiesid = facultiesid;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
