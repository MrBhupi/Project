package com.project.Project.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "subjects")
public class Subjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subjectid")  // ensure exact DB column
    private Long subjectid;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "full_mark")
    private Integer fullMark;

    @Column(name = "pass_marks")
    private Integer passMarks;  // rename to camelCase in Java, map to DB column

    @Column(name = "created_at")
    private Timestamp createdAt;

    // Getters and setters
    public Long getSubjectid() { return subjectid; }
    public void setSubjectid(Long subjectid) { this.subjectid = subjectid; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getFullMark() { return fullMark; }
    public void setFullMark(Integer fullMark) { this.fullMark = fullMark; }

    public Integer getPassMarks() { return passMarks; }
    public void setPassMarks(Integer passMarks) { this.passMarks = passMarks; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
