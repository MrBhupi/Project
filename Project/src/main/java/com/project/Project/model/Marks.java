package com.project.Project.model;


import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "marks")
public class Marks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long marksid;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Students student;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subjects subject;

    @ManyToOne
    @JoinColumn(name = "term_id", nullable = false)
    private Terms term;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users uploadedBy;

    private Float obtained_marks;
    private String remark;

    @Column(name = "uploaded_at")
    private Timestamp uploadedAt;

    private Long entry_by;

    public Long getMarksid() {
        return marksid;
    }

    public void setMarksid(Long marksid) {
        this.marksid = marksid;
    }

    public Students getStudent() { return student; }
    public void setStudent(Students student) { this.student = student; }

    public Subjects getSubject() { return subject; }
    public void setSubject(Subjects subject) { this.subject = subject; }

    public Terms getTerm() { return term; }
    public void setTerm(Terms term) { this.term = term; }

    public Users getUploadedBy() { return uploadedBy; }
    public void setUploadedBy(Users uploadedBy) { this.uploadedBy = uploadedBy; }

    public Float getObtained_marks() { return obtained_marks; }
    public void setObtained_marks(Float obtained_marks) {
        this.obtained_marks = obtained_marks;
    }

    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }

    public Timestamp getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(Timestamp uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public Long getEntry_by() { return entry_by; }
    public void setEntry_by(Long entry_by) { this.entry_by = entry_by; }
}
