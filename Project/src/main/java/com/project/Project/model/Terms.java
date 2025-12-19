package com.project.Project.model;


import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "terms")
public class Terms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long termid;

    private String name;
    private Date start_date;
    private Date end_date;

    @Column(name = "created_at")
    private Timestamp createdAt;

    public Long getTermid() {
        return termid;
    }

    public void setTermid(Long termid) {
        this.termid = termid;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Date getStart_date() { return start_date; }
    public void setStart_date(Date start_date) { this.start_date = start_date; }

    public Date getEnd_date() { return end_date; }
    public void setEnd_date(Date end_date) { this.end_date = end_date; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
