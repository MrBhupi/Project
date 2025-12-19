package com.project.Project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "teacher_subjects")
public class Teacher_subjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_subjectid")
    private Long teacherSubjectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Teachers teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Subjects subject;

    @Column(name = "student_batch")
    private String studentBatch;

    @Column(name = "student_programid")
    private Integer studentProgramId;

    @Column(name = "student_semester")
    private Integer studentSemester;

    // Getters and Setters
    public Long getTeacherSubjectId() { return teacherSubjectId; }
    public void setTeacherSubjectId(Long teacherSubjectId) { this.teacherSubjectId = teacherSubjectId; }

    public Teachers getTeacher() { return teacher; }
    public void setTeacher(Teachers teacher) { this.teacher = teacher; }

    public Subjects getSubject() { return subject; }
    public void setSubject(Subjects subject) { this.subject = subject; }

    public String getStudentBatch() { return studentBatch; }
    public void setStudentBatch(String studentBatch) { this.studentBatch = studentBatch; }

    public Integer getStudentProgramId() { return studentProgramId; }
    public void setStudentProgramId(Integer studentProgramId) { this.studentProgramId = studentProgramId; }

    public Integer getStudentSemester() { return studentSemester; }
    public void setStudentSemester(Integer studentSemester) { this.studentSemester = studentSemester; }
}
