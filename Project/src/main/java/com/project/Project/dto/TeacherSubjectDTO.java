package com.project.Project.dto;

public class TeacherSubjectDTO {
    private Long teacherId;
    private Long subjectId;
    private String studentBatch;
    private Integer studentProgramId;
    private Integer studentSemester;

    // Getters and Setters
    public Long getTeacherId() { return teacherId; }
    public void setTeacherId(Long teacherId) { this.teacherId = teacherId; }

    public Long getSubjectId() { return subjectId; }
    public void setSubjectId(Long subjectId) { this.subjectId = subjectId; }

    public String getStudentBatch() { return studentBatch; }
    public void setStudentBatch(String studentBatch) { this.studentBatch = studentBatch; }

    public Integer getStudentProgramId() { return studentProgramId; }
    public void setStudentProgramId(Integer studentProgramId) { this.studentProgramId = studentProgramId; }

    public Integer getStudentSemester() { return studentSemester; }
    public void setStudentSemester(Integer studentSemester) { this.studentSemester = studentSemester; }
}
